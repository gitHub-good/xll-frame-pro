package com.xll.frame.starter.security.limiter.exception;

import com.xll.frame.starter.core.exception.BaseException;

/**
 * 功能描述: <br>
 * <p>
 *  <限流异常>
 * </p>
 * @author xuliangliang
 * @since 2025/2/16 19:15
 * @version 1.0.0
 */
public class RateLimiterException extends BaseException {

    public RateLimiterException(String message) {
        super(message);
    }

    public RateLimiterException(String message, Throwable cause) {
        super(message, cause);
    }
}
