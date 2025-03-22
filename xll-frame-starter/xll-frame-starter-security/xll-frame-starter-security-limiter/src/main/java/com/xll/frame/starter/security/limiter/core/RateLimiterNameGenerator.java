package com.xll.frame.starter.security.limiter.core;

import java.lang.reflect.Method;

/**
 * 功能描述: <br>
 * <p>
 *  <定义限流器名称生成器>
 * </p>
 * @author xuliangliang
 * @since 2025/2/16 19:15
 * @version 1.0.0
 */
@FunctionalInterface
public interface RateLimiterNameGenerator {

    String generate(Object target, Method method, Object... args);
}
