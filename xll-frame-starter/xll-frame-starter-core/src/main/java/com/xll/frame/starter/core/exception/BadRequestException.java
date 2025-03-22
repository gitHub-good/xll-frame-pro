package com.xll.frame.starter.core.exception;

import java.io.Serial;

/**
 * 功能描述: <br>
 * <p>
 *  <自定义验证异常-错误请求>
 * </p>
 * @author xuliangliang
 * @since 2025/2/6 20:56
 * @version 1.0.0
 */
public class BadRequestException extends BaseException {

    @Serial
    private static final long serialVersionUID = 1L;

    public BadRequestException() {
    }

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(Throwable cause) {
        super(cause);
    }

    public BadRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
