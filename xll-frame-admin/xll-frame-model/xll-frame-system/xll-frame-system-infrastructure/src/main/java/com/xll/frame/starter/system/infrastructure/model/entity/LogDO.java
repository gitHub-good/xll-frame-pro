package com.xll.frame.starter.system.infrastructure.model.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xll.frame.starter.system.infrastructure.enums.LogStatusEnum;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 功能描述: <br>
 * <p>
 *  <系统日志实体>
 * </p>
 * @author xuliangliang
 * @since 2025/3/23 00:32
 * @version 1.0.0
 */
@Data
@TableName("sys_log")
public class LogDO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId
    private Long id;

    /**
     * 链路 ID
     */
    private String traceId;

    /**
     * 日志描述
     */
    private String description;

    /**
     * 所属模块
     */
    private String module;

    /**
     * 请求 URL
     */
    private String requestUrl;

    /**
     * 请求方式
     */
    private String requestMethod;

    /**
     * 请求头
     */
    private String requestHeaders;

    /**
     * 请求体
     */
    private String requestBody;

    /**
     * 状态码
     */
    private Integer statusCode;

    /**
     * 响应头
     */
    private String responseHeaders;

    /**
     * 响应体
     */
    private String responseBody;

    /**
     * 耗时（ms）
     */
    private Long timeTaken;

    /**
     * IP
     */
    private String ip;

    /**
     * IP 归属地
     */
    private String address;

    /**
     * 浏览器
     */
    private String browser;

    /**
     * 操作系统
     */
    private String os;

    /**
     * 状态
     */
    private LogStatusEnum status;

    /**
     * 错误信息
     */
    private String errorMsg;

    /**
     * 创建人
     */
    private Long createUser;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;
}
