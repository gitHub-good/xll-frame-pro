package com.xll.frame.system.infrastructure.service;

import com.xll.frame.system.infrastructure.model.resp.MessageUnreadResp;

import java.util.List;

/**
 * 功能描述: <br>
 * <p>
 *  <消息和用户关联业务接口>
 * </p>
 * @author xuliangliang
 * @since 2025/3/23 01:10
 * @version 1.0.0
 */
public interface MessageUserService {

    /**
     * 根据用户 ID 查询未读消息数量
     *
     * @param userId   用户 ID
     * @param isDetail 是否查询详情
     * @return 未读消息信息
     */
    MessageUnreadResp countUnreadMessageByUserId(Long userId, Boolean isDetail);

    /**
     * 新增
     *
     * @param messageId  消息 ID
     * @param userIdList 用户 ID 列表
     */
    void add(Long messageId, List<Long> userIdList);

    /**
     * 将消息标记已读
     *
     * @param ids 消息ID（为空则将所有消息标记已读）
     */
    void readMessage(List<Long> ids);

    /**
     * 根据消息 ID 删除
     *
     * @param messageIds 消息 ID 列表
     */
    void deleteByMessageIds(List<Long> messageIds);
}