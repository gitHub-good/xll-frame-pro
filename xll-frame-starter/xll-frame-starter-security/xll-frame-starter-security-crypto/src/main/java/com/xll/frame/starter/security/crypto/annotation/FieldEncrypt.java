package com.xll.frame.starter.security.crypto.annotation;

import com.xll.frame.starter.security.crypto.encryptor.IEncryptor;
import com.xll.frame.starter.security.crypto.enums.Algorithm;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 功能描述: <br>
 * <p>
 *  <字段加/解密注解>
 * </p>
 * @author xuliangliang
 * @since 2025/2/6 21:58
 * @version 1.0.0
 */
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface FieldEncrypt {

    /**
     * 加密/解密算法
     */
    Algorithm value() default Algorithm.AES;

    /**
     * 加密/解密处理器
     * <p>
     * 优先级高于加密/解密算法
     * </p>
     */
    Class<? extends IEncryptor> encryptor() default IEncryptor.class;

    /**
     * 对称加密算法密钥
     */
    String password() default "";
}