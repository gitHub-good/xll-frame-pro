package com.xll.frame.starter.system.infrastructure.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.xll.frame.starter.common.model.entity.BaseDO;
import com.xll.frame.starter.system.infrastructure.enums.NoticeScopeEnum;
import lombok.Data;

import java.io.Serial;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 功能描述: <br>
 * <p>
 *  <公告实体>
 * </p>
 * @author xuliangliang
 * @since 2025/3/23 00:33
 * @version 1.0.0
 */
@Data
@TableName(value = "sys_notice", autoResultMap = true)
public class NoticeDO extends BaseDO {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 类型
     */
    private String type;

    /**
     * 生效时间
     */
    private LocalDateTime effectiveTime;

    /**
     * 终止时间
     */
    private LocalDateTime terminateTime;

    /**
     * 通知范围
     */
    private NoticeScopeEnum noticeScope;

    /**
     * 通知用户
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<String> noticeUsers;
}