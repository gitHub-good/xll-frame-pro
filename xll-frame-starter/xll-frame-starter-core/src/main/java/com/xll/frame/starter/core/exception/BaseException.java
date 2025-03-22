package com.xll.frame.starter.core.exception;

import java.io.Serial;

/**
 * 功能描述: <br>
 * <p>
 *  <自定义异常基类>
 * </p>
 * @author xuliangliang
 * @since 2025/2/6 20:56
 * @version 1.0.0
 */
public class BaseException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public BaseException() {
    }

    public BaseException(String message) {
        super(message);
    }

    public BaseException(Throwable cause) {
        super(cause);
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }
}
