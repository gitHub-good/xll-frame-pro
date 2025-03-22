package com.xll.frame.system.domain.impl;

import cn.hutool.core.collection.CollUtil;
import com.xll.frame.starter.core.validation.CheckUtils;
import com.xll.frame.system.domain.MessageUserService;
import com.xll.frame.system.infrastructure.enums.MessageTypeEnum;
import com.xll.frame.system.infrastructure.model.entity.MessageUserDO;
import com.xll.frame.system.infrastructure.model.resp.MessageTypeUnreadResp;
import com.xll.frame.system.infrastructure.model.resp.MessageUnreadResp;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.xll.frame.system.infrastructure.mapper.MessageUserMapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 功能描述: <br>
 * <p>
 *  <消息和用户关联业务实现>
 * </p>
 * @author xuliangliang
 * @since 2025/3/23 01:10
 * @version 1.0.0
 */
@Service
@RequiredArgsConstructor
public class MessageUserServiceImpl implements MessageUserService {

    private final MessageUserMapper baseMapper;

    @Override
    public MessageUnreadResp countUnreadMessageByUserId(Long userId, Boolean isDetail) {
        MessageUnreadResp result = new MessageUnreadResp();
        Long total = 0L;
        if (Boolean.TRUE.equals(isDetail)) {
            List<MessageTypeUnreadResp> detailList = new ArrayList<>();
            for (MessageTypeEnum messageType : MessageTypeEnum.values()) {
                MessageTypeUnreadResp resp = new MessageTypeUnreadResp();
                resp.setType(messageType);
                Long count = baseMapper.selectUnreadCountByUserIdAndType(userId, messageType.getValue());
                resp.setCount(count);
                detailList.add(resp);
                total += count;
            }
            result.setDetails(detailList);
        } else {
            total = baseMapper.selectUnreadCountByUserIdAndType(userId, null);
        }
        result.setTotal(total);
        return result;
    }

    @Override
    public void add(Long messageId, List<Long> userIdList) {
        CheckUtils.throwIfEmpty(userIdList, "消息接收人不能为空");
        List<MessageUserDO> messageUserList = userIdList.stream().map(userId -> {
            MessageUserDO messageUser = new MessageUserDO();
            messageUser.setUserId(userId);
            messageUser.setMessageId(messageId);
            messageUser.setIsRead(false);
            return messageUser;
        }).toList();
        baseMapper.insert(messageUserList);
    }

    @Override
    public void readMessage(List<Long> ids) {
        if (CollUtil.isEmpty(ids)) {
            return;
        }
        baseMapper.lambdaUpdate()
            .set(MessageUserDO::getIsRead, true)
            .set(MessageUserDO::getReadTime, LocalDateTime.now())
            .eq(MessageUserDO::getIsRead, false)
            .in(CollUtil.isNotEmpty(ids), MessageUserDO::getMessageId, ids)
            .update();
    }

    @Override
    public void deleteByMessageIds(List<Long> messageIds) {
        if (CollUtil.isEmpty(messageIds)) {
            return;
        }
        baseMapper.lambdaUpdate().in(MessageUserDO::getMessageId, messageIds).remove();
    }
}