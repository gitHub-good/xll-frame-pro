package com.xll.frame.starter.system.application.auth;

import com.xll.frame.starter.extension.crud.core.model.query.PageQuery;
import com.xll.frame.starter.extension.crud.mp.model.resp.PageResp;
import com.xll.frame.starter.system.infrastructure.auth.model.query.OnlineUserQuery;
import com.xll.frame.starter.system.infrastructure.auth.model.resp.OnlineUserResp;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 功能描述: <br>
 * <p>
 *  <在线用户业务接口>
 * </p>
 * @author xuliangliang
 * @since 2025/2/23 17:25
 * @version 1.0.0
 */
public interface OnlineUserService {

    /**
     * 分页查询列表
     *
     * @param query     查询条件
     * @param pageQuery 分页查询条件
     * @return 分页列表信息
     */
    PageResp<OnlineUserResp> page(OnlineUserQuery query, PageQuery pageQuery);

    /**
     * 查询列表
     *
     * @param query 查询条件
     * @return 列表信息
     */
    List<OnlineUserResp> list(OnlineUserQuery query);

    /**
     * 查询 Token 最后活跃时间
     *
     * @param token Token
     * @return 最后活跃时间
     */
    LocalDateTime getLastActiveTime(String token);

    /**
     * 踢出用户
     *
     * @param userId 用户 ID
     */
    void kickOut(Long userId);
}
