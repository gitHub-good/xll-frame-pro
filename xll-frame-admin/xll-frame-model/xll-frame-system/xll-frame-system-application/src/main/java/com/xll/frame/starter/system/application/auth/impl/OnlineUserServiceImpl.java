package com.xll.frame.starter.system.application.auth.impl;

import cn.crane4j.annotation.AutoOperate;
import cn.dev33.satoken.dao.SaTokenDao;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.xll.frame.starter.common.context.UserContext;
import com.xll.frame.starter.common.context.UserContextHolder;
import com.xll.frame.starter.common.context.UserExtraContext;
import com.xll.frame.starter.core.constant.StringConstants;
import com.xll.frame.starter.extension.crud.core.model.query.PageQuery;
import com.xll.frame.starter.extension.crud.mp.model.resp.PageResp;
import com.xll.frame.starter.system.application.auth.OnlineUserService;
import com.xll.frame.starter.system.infrastructure.auth.model.query.OnlineUserQuery;
import com.xll.frame.starter.system.infrastructure.auth.model.resp.OnlineUserResp;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 功能描述: <br>
 * <p>
 *  <在线用户业务实现>
 * </p>
 * @author xuliangliang
 * @since 2025/3/23 01:24
 * @version 1.0.0
 */
@Service
@RequiredArgsConstructor
public class OnlineUserServiceImpl implements OnlineUserService {

    @Override
    @AutoOperate(type = OnlineUserResp.class, on = "list")
    public PageResp<OnlineUserResp> page(OnlineUserQuery query, PageQuery pageQuery) {
        List<OnlineUserResp> list = this.list(query);
        return PageResp.build(pageQuery.getPage(), pageQuery.getSize(), list);
    }

    @Override
    public List<OnlineUserResp> list(OnlineUserQuery query) {
        List<OnlineUserResp> list = new ArrayList<>();
        // 查询所有在线 Token
        List<String> tokenKeyList = StpUtil.searchTokenValue(StringConstants.EMPTY, 0, -1, false);
        Map<Long, List<String>> tokenMap = tokenKeyList.stream().filter(tokenKey -> {
            String token = StrUtil.subAfter(tokenKey, StringConstants.COLON, true);
            // 忽略已过期或失效 Token
            return StpUtil.getStpLogic().getTokenActiveTimeoutByToken(token) >= SaTokenDao.NEVER_EXPIRE;
        })
            .map(tokenKey -> StrUtil.subAfter(tokenKey, StringConstants.COLON, true))
            .collect(Collectors.groupingBy(token -> Convert.toLong(StpUtil.getLoginIdByToken(token))));
        // 筛选数据
        for (Map.Entry<Long, List<String>> entry : tokenMap.entrySet()) {
            Long userId = entry.getKey();
            UserContext userContext = UserContextHolder.getContext(userId);
            if (null == userContext || !this.isMatchNickname(query.getNickname(), userContext) || !this
                .isMatchClientId(query.getClientId(), userContext)) {
                continue;
            }
            List<Date> loginTimeList = query.getLoginTime();
            entry.getValue().parallelStream().forEach(token -> {
                UserExtraContext extraContext = UserContextHolder.getExtraContext(token);
                if (!this.isMatchLoginTime(loginTimeList, extraContext.getLoginTime())) {
                    return;
                }
                OnlineUserResp resp = BeanUtil.copyProperties(userContext, OnlineUserResp.class);
                BeanUtil.copyProperties(extraContext, resp);
                resp.setToken(token);
                list.add(resp);
            });
        }
        // 设置排序
        CollUtil.sort(list, Comparator.comparing(OnlineUserResp::getLoginTime).reversed());
        return list;
    }

    @Override
    public LocalDateTime getLastActiveTime(String token) {
        long lastActiveTime = StpUtil.getStpLogic().getTokenLastActiveTime(token);
        return lastActiveTime == SaTokenDao.NOT_VALUE_EXPIRE ? null : DateUtil.date(lastActiveTime).toLocalDateTime();
    }

    @Override
    public void kickOut(Long userId) {
        if (!StpUtil.isLogin(userId)) {
            return;
        }
        StpUtil.logout(userId);
    }

    /**
     * 是否匹配昵称
     *
     * @param nickname    昵称
     * @param userContext 用户上下文信息
     * @return 是否匹配昵称
     */
    private boolean isMatchNickname(String nickname, UserContext userContext) {
        if (StrUtil.isBlank(nickname)) {
            return true;
        }
        return StrUtil.contains(userContext.getUsername(), nickname) || StrUtil.contains(UserContextHolder
            .getNickname(userContext.getId()), nickname);
    }

    /**
     * 是否匹配客户端 ID
     *
     * @param clientId    客户端 ID
     * @param userContext 用户上下文信息
     * @return 是否匹配客户端 ID
     */
    private boolean isMatchClientId(String clientId, UserContext userContext) {
        if (StrUtil.isBlank(clientId)) {
            return true;
        }
        return Objects.equals(userContext.getClientId(), clientId);
    }

    /**
     * 是否匹配登录时间
     *
     * @param loginTimeList 登录时间列表
     * @param loginTime     登录时间
     * @return 是否匹配登录时间
     */
    private boolean isMatchLoginTime(List<Date> loginTimeList, LocalDateTime loginTime) {
        if (CollUtil.isEmpty(loginTimeList)) {
            return true;
        }
        return DateUtil.isIn(DateUtil.date(loginTime).toJdkDate(), loginTimeList.get(0), loginTimeList.get(1));
    }
}
