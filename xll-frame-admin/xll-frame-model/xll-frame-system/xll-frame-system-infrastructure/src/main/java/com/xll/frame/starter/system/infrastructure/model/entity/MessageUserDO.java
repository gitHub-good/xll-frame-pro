package com.xll.frame.starter.system.infrastructure.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 功能描述: <br>
 * <p>
 *  <消息和用户关联实体>
 * </p>
 * @author xuliangliang
 * @since 2025/3/23 00:33
 * @version 1.0.0
 */
@Data
@TableName("sys_message_user")
public class MessageUserDO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 消息 ID
     */
    private Long messageId;

    /**
     * 用户 ID
     */
    private Long userId;

    /**
     * 是否已读
     */
    private Boolean isRead;

    /**
     * 读取时间
     */
    private LocalDateTime readTime;
}