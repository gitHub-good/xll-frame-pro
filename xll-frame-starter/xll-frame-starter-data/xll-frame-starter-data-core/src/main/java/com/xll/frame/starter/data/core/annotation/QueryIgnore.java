package com.xll.frame.starter.data.core.annotation;

import java.lang.annotation.*;

/**
 * 功能描述: <br>
 * <p>
 *  <查询解析忽略注解>
 * </p>
 * @author xuliangliang
 * @since 2025/2/6 21:30
 * @version 1.0.0
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface QueryIgnore {}
