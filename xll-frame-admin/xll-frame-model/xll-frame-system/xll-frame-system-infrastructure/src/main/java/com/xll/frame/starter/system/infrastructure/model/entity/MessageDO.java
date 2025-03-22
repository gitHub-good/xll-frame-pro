package com.xll.frame.starter.system.infrastructure.model.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xll.frame.starter.system.infrastructure.enums.MessageTypeEnum;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 功能描述: <br>
 * <p>
 *  <消息实体>
 * </p>
 * @author xuliangliang
 * @since 2025/3/23 00:33
 * @version 1.0.0
 */
@Data
@TableName("sys_message")
public class MessageDO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId
    private Long id;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 类型（1：系统消息）
     */
    private MessageTypeEnum type;

    /**
     * 创建人
     */
    @TableField(fill = FieldFill.INSERT)
    private Long createUser;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
}