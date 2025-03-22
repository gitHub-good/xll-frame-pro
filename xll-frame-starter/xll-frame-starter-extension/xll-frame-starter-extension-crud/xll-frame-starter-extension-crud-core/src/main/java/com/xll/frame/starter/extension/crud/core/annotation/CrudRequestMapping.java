package com.xll.frame.starter.extension.crud.core.annotation;

import com.xll.frame.starter.extension.crud.core.enums.Api;

import java.lang.annotation.*;

/**
 * 功能描述: <br>
 * <p>
 *  <CRUD（增删改查）请求映射器注解>
 * </p>
 * @author xuliangliang
 * @since 2025/2/16 20:03
 * @version 1.0.0
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CrudRequestMapping {

    /**
     * 路径映射 URI（等同于：@RequestMapping("/foo1")）
     */
    String value() default "";

    /**
     * API 列表
     */
    Api[] api() default {Api.PAGE, Api.DETAIL, Api.ADD, Api.UPDATE, Api.DELETE, Api.EXPORT};
}
