package com.xll.frame.starter.core.constant;

/**
 * 功能描述: <br>
 * <p>
 *  <配置属性相关常量>
 * </p>
 * @author xuliangliang
 * @since 2025/2/6 20:34
 * @version 1.0.0
 */
public class PropertiesConstants {

    /**
     * ContiNew Starter
     */
    public static final String FRAME_STARTER = "frame-starter";

    /**
     * 启用配置
     */
    public static final String ENABLED = "enabled";

    /**
     * 安全配置
     */
    public static final String SECURITY = FRAME_STARTER + StringConstants.DOT + "security";

    /**
     * 密码编解码配置
     */
    public static final String SECURITY_PASSWORD = SECURITY + StringConstants.DOT + "password";

    /**
     * 加/解密配置
     */
    public static final String SECURITY_CRYPTO = SECURITY + StringConstants.DOT + "crypto";

    /**
     * 限流器配置
     */
    public static final String SECURITY_LIMITER = SECURITY + StringConstants.DOT + "limiter";

    /**
     * Web 配置
     */
    public static final String WEB = FRAME_STARTER + StringConstants.DOT + "web";

    /**
     * 跨域配置
     */
    public static final String WEB_CORS = WEB + StringConstants.DOT + "cors";

    /**
     * 响应配置
     */
    public static final String WEB_RESPONSE = WEB + StringConstants.DOT + "response";

    /**
     * 链路配置
     */
    public static final String WEB_TRACE = WEB + StringConstants.DOT + "trace";

    /**
     * XSS 配置
     */
    public static final String WEB_XSS = WEB + StringConstants.DOT + "xss";

    /**
     * 日志配置
     */
    public static final String LOG = FRAME_STARTER + StringConstants.DOT + "log";

    /**
     * 存储配置
     */
    public static final String STORAGE = FRAME_STARTER + StringConstants.DOT + "storage";

    /**
     * 本地存储配置
     */
    public static final String STORAGE_LOCAL = STORAGE + StringConstants.DOT + "local";

    /**
     * 验证码配置
     */
    public static final String CAPTCHA = FRAME_STARTER + StringConstants.DOT + "captcha";

    /**
     * 图形验证码配置
     */
    public static final String CAPTCHA_GRAPHIC = CAPTCHA + StringConstants.DOT + "graphic";

    /**
     * 行为验证码配置
     */
    public static final String CAPTCHA_BEHAVIOR = CAPTCHA + StringConstants.DOT + "behavior";

    /**
     * 消息配置
     */
    public static final String MESSAGING = FRAME_STARTER + StringConstants.DOT + "messaging";

    /**
     * WebSocket 配置
     */
    public static final String MESSAGING_WEBSOCKET = MESSAGING + StringConstants.DOT + "websocket";

    /**
     * CRUD 配置
     */
    public static final String CRUD = FRAME_STARTER + StringConstants.DOT + "crud";

    /**
     * 数据权限配置
     */
    public static final String DATA_PERMISSION = FRAME_STARTER + StringConstants.DOT + "data-permission";

    /**
     * 多租户配置
     */
    public static final String TENANT = FRAME_STARTER + StringConstants.DOT + "tenant";

    private PropertiesConstants() {
    }
}
