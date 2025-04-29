package com.xll.frame.system.domain.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.xll.frame.system.infrastructure.service.UserPasswordHistoryService;
import com.xll.frame.system.infrastructure.model.entity.UserPasswordHistoryDO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.xll.frame.system.infrastructure.mapper.UserPasswordHistoryMapper;

import java.util.List;

/**
 * 功能描述: <br>
 * <p>
 *  <用户历史密码业务实现>
 * </p>
 * @author xuliangliang
 * @since 2025/3/23 01:22
 * @version 1.0.0
 */
@Service
@RequiredArgsConstructor
public class UserPasswordHistoryServiceImpl implements UserPasswordHistoryService {

    private final UserPasswordHistoryMapper baseMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(Long userId, String password, int count) {
        if (StrUtil.isBlank(password)) {
            return;
        }
        baseMapper.insert(new UserPasswordHistoryDO(userId, password));
        // 删除过期历史密码
        baseMapper.deleteExpired(userId, count);
    }

    @Override
    public void deleteByUserIds(List<Long> userIds) {
        if (CollUtil.isEmpty(userIds)) {
            return;
        }
        baseMapper.lambdaUpdate().in(UserPasswordHistoryDO::getUserId, userIds).remove();
    }

    @Override
    public boolean isPasswordReused(Long userId, String password, int count) {
        // 查询近 N 个历史密码
        List<UserPasswordHistoryDO> list = baseMapper.lambdaQuery()
            .select(UserPasswordHistoryDO::getPassword)
            .eq(UserPasswordHistoryDO::getUserId, userId)
            .orderByDesc(UserPasswordHistoryDO::getCreateTime)
            .last("LIMIT %s".formatted(count))
            .list();
        if (CollUtil.isEmpty(list)) {
            return false;
        }
        // 校验是否重复使用历史密码
        List<String> passwordList = list.stream().map(UserPasswordHistoryDO::getPassword).toList();
        return passwordList.stream().anyMatch(p -> passwordEncoder.matches(password, p));
    }
}