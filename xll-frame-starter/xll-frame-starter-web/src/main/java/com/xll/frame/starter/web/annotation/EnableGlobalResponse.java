package com.xll.frame.starter.web.annotation;

import com.xll.frame.starter.web.autoconfigure.response.GlobalResponseAutoConfiguration;
import org.springframework.context.annotation.Import;
import java.lang.annotation.*;

/**
 * 功能描述: <br>
 * <p>
 *  <全局响应启用注解>
 * </p>
 * @author xuliangliang
 * @since 2025/2/16 18:41
 * @version 1.0.0
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({GlobalResponseAutoConfiguration.class})
public @interface EnableGlobalResponse {}
