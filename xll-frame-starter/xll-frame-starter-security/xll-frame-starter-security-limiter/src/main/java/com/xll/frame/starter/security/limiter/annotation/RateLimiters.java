package com.xll.frame.starter.security.limiter.annotation;

import java.lang.annotation.*;

/**
 * 功能描述: <br>
 * <p>
 *  <限流组注解>
 * </p>
 * @author xuliangliang
 * @since 2025/2/16 19:13
 * @version 1.0.0
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface RateLimiters {

    /**
     * 限流组
     */
    RateLimiter[] value();
}
