package com.xll.frame.starter.extension.crud.core.annotation;

import com.xll.frame.starter.extension.crud.core.autoconfigure.CrudRequestMappingAutoConfiguration;
import com.xll.frame.starter.extension.crud.core.autoconfigure.CrudRestControllerAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 功能描述: <br>
 * <p>
 *  <CRUD REST Controller 启用注解>
 * </p>
 * @author xuliangliang
 * @since 2025/2/16 20:04
 * @version 1.0.0
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({CrudRequestMappingAutoConfiguration.class, CrudRestControllerAutoConfiguration.class})
public @interface EnableCrudRestController {}
