package com.xll.frame.starter.cache.redisson.autoconfigure;

import lombok.Data;
import org.redisson.config.ClusterServersConfig;
import org.redisson.config.SentinelServersConfig;
import org.redisson.config.SingleServerConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 功能描述: <br>
 * <p>
 *  <Redisson 配置属性>
 * </p>
 * @author xuliangliang
 * @since 2025/2/16 19:11
 * @version 1.0.0
 */
@Data
@ConfigurationProperties("spring.data.redisson")
public class RedissonProperties {

    /**
     * 是否启用 Redisson
     */
    private boolean enabled = true;

    /**
     * Redis 模式
     */
    private Mode mode = Mode.SINGLE;

    /**
     * 单机服务配置
     */
    private SingleServerConfig singleServerConfig;

    /**
     * 集群服务配置
     */
    private ClusterServersConfig clusterServersConfig;

    /**
     * 哨兵服务配置
     */
    private SentinelServersConfig sentinelServersConfig;

    /**
     * Redis 模式
     */
    public enum Mode {
        /**
         * 单机
         */
        SINGLE,

        /**
         * 集群
         */
        CLUSTER,

        /**
         * 哨兵
         */
        SENTINEL
    }
}
