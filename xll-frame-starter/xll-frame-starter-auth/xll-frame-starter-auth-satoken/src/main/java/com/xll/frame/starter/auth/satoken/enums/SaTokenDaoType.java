package com.xll.frame.starter.auth.satoken.enums;

/**
 * 功能描述: <br>
 * <p>
 *  <SaToken 持久层类型枚举>
 * </p>
 * @author xuliangliang
 * @since 2025/2/23 16:19
 * @version 1.0.0
 */
public enum SaTokenDaoType {

    /**
     * 默认（内存）
     */
    DEFAULT,

    /**
     * Redis
     */
    REDIS,

    /**
     * 自定义
     */
    CUSTOM
}
