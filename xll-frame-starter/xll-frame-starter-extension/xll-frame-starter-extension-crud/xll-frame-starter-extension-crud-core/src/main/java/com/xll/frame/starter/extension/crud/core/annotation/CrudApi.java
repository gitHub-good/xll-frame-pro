package com.xll.frame.starter.extension.crud.core.annotation;

import com.xll.frame.starter.extension.crud.core.enums.Api;

import java.lang.annotation.*;

/**
 * 功能描述: <br>
 * <p>
 *  <CRUD（增删改查）API>
 * </p>
 * @author xuliangliang
 * @since 2025/2/16 20:03
 * @version 1.0.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CrudApi {

    /**
     * API 类型
     */
    Api value() default Api.LIST;
}
