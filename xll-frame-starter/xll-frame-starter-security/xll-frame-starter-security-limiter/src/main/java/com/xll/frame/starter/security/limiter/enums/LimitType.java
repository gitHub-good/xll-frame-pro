package com.xll.frame.starter.security.limiter.enums;

/**
 * 功能描述: <br>
 * <p>
 *  <限流类型>
 * </p>
 * @author xuliangliang
 * @since 2025/2/16 19:16
 * @version 1.0.0
 */
public enum LimitType {

    /**
     * 全局限流
     */
    DEFAULT,

    /**
     * 根据 IP 限流
     */
    IP,

    /**
     * 根据实例限流（支持集群多实例）
     */
    CLUSTER
}
