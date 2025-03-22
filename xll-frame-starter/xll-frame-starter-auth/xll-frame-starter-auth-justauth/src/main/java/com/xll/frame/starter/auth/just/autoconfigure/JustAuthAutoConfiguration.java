package com.xll.frame.starter.auth.just.autoconfigure;

import com.xll.frame.starter.auth.just.core.AuthStateCacheRedisDefaultImpl;
import com.xll.frame.starter.core.constant.PropertiesConstants;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import me.zhyd.oauth.cache.AuthStateCache;
import org.redisson.client.RedisClient;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;

/**
 * 功能描述: <br>
 * <p>
 *  <JustAuth 自动配置>
 * </p>
 * @author xuliangliang
 * @since 2025/2/23 16:01
 * @version 1.0.0
 */
@Slf4j
@AutoConfiguration(before = com.xkcoding.justauth.autoconfigure.JustAuthAutoConfiguration.class)
@ConditionalOnProperty(prefix = "justauth", name = PropertiesConstants.ENABLED, havingValue = "true", matchIfMissing = true)
public class JustAuthAutoConfiguration {

    /**
     * State 缓存 Redis 实现（默认）
     */
    @Bean
    @ConditionalOnClass(RedisClient.class)
    @ConditionalOnProperty(prefix = "justauth.cache", name = "type", havingValue = "redis")
    public AuthStateCache authStateCache() {
        AuthStateCacheRedisDefaultImpl impl = new AuthStateCacheRedisDefaultImpl();
        log.debug("[Frame Starter] - Auto Configuration 'JustAuth-AuthStateCache-Redis' completed initialization.");
        return impl;
    }

    @PostConstruct
    public void postConstruct() {
        log.debug("[Frame Starter] - Auto Configuration 'JustAuth' completed initialization.");
    }
}