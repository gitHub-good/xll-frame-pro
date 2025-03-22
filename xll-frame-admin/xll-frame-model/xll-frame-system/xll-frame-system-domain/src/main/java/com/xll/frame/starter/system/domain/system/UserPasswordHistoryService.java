package com.xll.frame.starter.system.domain.system;

import java.util.List;

/**
 * 功能描述: <br>
 * <p>
 *  <用户历史密码业务接口>
 * </p>
 * @author xuliangliang
 * @since 2025/3/23 01:22
 * @version 1.0.0
 */
public interface UserPasswordHistoryService {

    /**
     * 新增
     *
     * @param userId   用户 ID
     * @param password 密码
     * @param count    保留 N 个历史
     */
    void add(Long userId, String password, int count);

    /**
     * 根据用户 ID 删除
     *
     * @param userIds 用户 ID 列表
     */
    void deleteByUserIds(List<Long> userIds);

    /**
     * 密码是否为重复使用
     *
     * @param userId   用户 ID
     * @param password 密码
     * @param count    最近 N 次
     * @return 是否为重复使用
     */
    boolean isPasswordReused(Long userId, String password, int count);
}