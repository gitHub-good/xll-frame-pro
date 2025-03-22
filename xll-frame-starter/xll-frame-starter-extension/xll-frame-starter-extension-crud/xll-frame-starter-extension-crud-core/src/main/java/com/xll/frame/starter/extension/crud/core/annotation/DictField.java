package com.xll.frame.starter.extension.crud.core.annotation;

import java.lang.annotation.*;

/**
 * 功能描述: <br>
 * <p>
 *  <字典结构字段>
 * </p>
 * @author xuliangliang
 * @since 2025/2/16 20:04
 * @version 1.0.0
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DictField {

    /**
     * 标签字段名
     *
     * @return 标签字段名
     */
    String labelKey() default "name";

    /**
     * 值字段名
     *
     * @return 值字段名
     */
    String valueKey() default "id";

    /**
     * 额外信息字段名
     *
     * @return 额外信息字段名
     */
    String[] extraKeys() default {};
}
