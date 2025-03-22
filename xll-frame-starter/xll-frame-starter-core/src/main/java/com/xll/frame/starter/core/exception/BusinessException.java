package com.xll.frame.starter.core.exception;

import java.io.Serial;

/**
 * 功能描述: <br>
 * <p>
 *  <业务异常>
 * </p>
 * @author xuliangliang
 * @since 2025/2/6 20:57
 * @version 1.0.0
 */
public class BusinessException extends BaseException {

    @Serial
    private static final long serialVersionUID = 1L;

    public BusinessException() {
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }
}
