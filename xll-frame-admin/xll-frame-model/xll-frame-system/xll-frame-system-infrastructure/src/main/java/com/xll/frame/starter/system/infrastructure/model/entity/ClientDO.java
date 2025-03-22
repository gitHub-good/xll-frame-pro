package com.xll.frame.starter.system.infrastructure.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.xll.frame.starter.common.enums.DisEnableStatusEnum;
import com.xll.frame.starter.common.model.entity.BaseDO;
import lombok.Data;

import java.io.Serial;
import java.util.List;

/**
 * 功能描述: <br>
 * <p>
 *  <客户端实体>
 * </p>
 * @author xuliangliang
 * @since 2025/2/23 18:12
 * @version 1.0.0
 */
@Data
@TableName(value = "sys_client", autoResultMap = true)
public class ClientDO extends BaseDO {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 客户端 ID
     */
    private String clientId;

    /**
     * 客户端 Key
     */
    private String clientKey;

    /**
     * 客户端秘钥
     */
    private String clientSecret;

    /**
     * 登录类型
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<String> authType;

    /**
     * 客户端类型
     */
    private String clientType;

    /**
     * Token 最低活跃频率（单位：秒，-1：不限制，永不冻结）
     */
    private Long activeTimeout;

    /**
     * Token 有效期（单位：秒，-1：永不过期）
     */
    private Long timeout;

    /**
     * 状态
     */
    private DisEnableStatusEnum status;
}