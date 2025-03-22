package com.xll.frame.system.infrastructure.mapper;

import com.xll.frame.starter.data.mp.base.BaseMapper;
import com.xll.frame.system.infrastructure.model.entity.UserPasswordHistoryDO;
import org.apache.ibatis.annotations.Param;

/**
 * 功能描述: <br>
 * <p>
 *  <用户历史密码 Mapper>
 * </p>
 * @author xuliangliang
 * @since 2025/3/23 00:31
 * @version 1.0.0
 */
public interface UserPasswordHistoryMapper extends BaseMapper<UserPasswordHistoryDO> {

    /**
     * 删除过期历史密码
     *
     * @param userId 用户 ID
     * @param count  保留 N 个历史
     */
    void deleteExpired(@Param("userId") Long userId, @Param("count") int count);
}