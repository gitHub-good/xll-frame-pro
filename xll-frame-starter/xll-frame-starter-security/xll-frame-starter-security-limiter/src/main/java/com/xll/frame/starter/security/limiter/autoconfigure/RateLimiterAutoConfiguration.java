package com.xll.frame.starter.security.limiter.autoconfigure;

import com.xll.frame.starter.core.constant.PropertiesConstants;
import com.xll.frame.starter.security.limiter.core.DefaultRateLimiterNameGenerator;
import com.xll.frame.starter.security.limiter.core.RateLimiterNameGenerator;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

/**
 * 功能描述: <br>
 * <p>
 *  <限流器自动配置>
 * </p>
 * @author xuliangliang
 * @since 2025/2/16 19:13
 * @version 1.0.0
 */
@AutoConfiguration
@EnableConfigurationProperties(RateLimiterProperties.class)
@ComponentScan({"com.xll.frame.starter.security.limiter.core"})
@ConditionalOnProperty(prefix = PropertiesConstants.SECURITY_LIMITER, name = PropertiesConstants.ENABLED, havingValue = "true", matchIfMissing = true)
public class RateLimiterAutoConfiguration {

    private static final Logger log = LoggerFactory.getLogger(RateLimiterAutoConfiguration.class);

    /**
     * 限流器名称生成器
     */
    @Bean
    @ConditionalOnMissingBean
    public RateLimiterNameGenerator nameGenerator() {
        return new DefaultRateLimiterNameGenerator();
    }

    @PostConstruct
    public void postConstruct() {
        log.debug("[Frame Starter] - Auto Configuration 'Security-RateLimiter' completed initialization.");
    }
}
