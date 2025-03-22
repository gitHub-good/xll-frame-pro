package com.xll.frame.starter.security.limiter.autoconfigure;

import com.xll.frame.starter.core.constant.PropertiesConstants;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 功能描述: <br>
 * <p>
 *  <限流器配置属性>
 * </p>
 * @author xuliangliang
 * @since 2025/2/16 19:14
 * @version 1.0.0
 */
@Data
@ConfigurationProperties(PropertiesConstants.SECURITY_LIMITER)
public class RateLimiterProperties {

    /**
     * Key 前缀
     */
    private String keyPrefix = "RateLimiter";

}
