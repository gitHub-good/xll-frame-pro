package com.xll.frame.system.infrastructure.model.resp;

import cn.crane4j.annotation.Assemble;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xll.frame.starter.common.constant.ContainerConstants;
import com.xll.frame.system.infrastructure.enums.MessageTypeEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Schema(description = "消息信息")
public class MessageResp implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @Schema(description = "ID", example = "1")
    private Long id;

    /**
     * 标题
     */
    @Schema(description = "标题", example = "欢迎注册 xxx")
    private String title;

    /**
     * 内容
     */
    @Schema(description = "内容", example = "尊敬的 xx，欢迎注册使用，请及时配置您的密码。")
    private String content;

    /**
     * 类型
     */
    @Schema(description = "类型（1：系统消息）", example = "1")
    private MessageTypeEnum type;

    /**
     * 是否已读
     */
    @Schema(description = "是否已读", example = "true")
    private Boolean isRead;

    /**
     * 读取时间
     */
    @Schema(description = "读取时间", example = "2023-08-08 23:59:59", type = "string")
    private LocalDateTime readTime;

    /**
     * 创建人
     */
    @JsonIgnore
    @Assemble(prop = ":createUserString", container = ContainerConstants.USER_NICKNAME)
    private Long createUser;

    /**
     * 创建人
     */
    @Schema(description = "创建人", example = "超级管理员")
    private String createUserString;

    /**
     * 创建时间
     */
    @Schema(description = "创建时间", example = "2023-08-08 08:08:08", type = "string")
    private LocalDateTime createTime;
}