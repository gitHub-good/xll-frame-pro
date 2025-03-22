package com.xll.frame.system.infrastructure.mapper;

import com.xll.frame.starter.data.mp.base.BaseMapper;
import com.xll.frame.system.infrastructure.model.entity.MessageUserDO;
import org.apache.ibatis.annotations.Param;

/**
 * 功能描述: <br>
 * <p>
 *  <消息和用户 Mapper>
 * </p>
 * @author xuliangliang
 * @since 2025/3/23 00:29
 * @version 1.0.0
 */
public interface MessageUserMapper extends BaseMapper<MessageUserDO> {

    /**
     * 根据用户 ID 和消息类型查询未读消息数量
     *
     * @param userId 用户 ID
     * @param type   消息类型
     * @return 未读消息信息
     */
    Long selectUnreadCountByUserIdAndType(@Param("userId") Long userId, @Param("type") Integer type);
}