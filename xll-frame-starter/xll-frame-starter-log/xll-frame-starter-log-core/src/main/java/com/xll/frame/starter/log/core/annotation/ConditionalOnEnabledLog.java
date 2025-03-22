package com.xll.frame.starter.log.core.annotation;

import com.xll.frame.starter.core.constant.PropertiesConstants;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

import java.lang.annotation.*;

/**
 * 功能描述: <br>
 * <p>
 *  <是否启用日志记录注解>
 * </p>
 * @author xuliangliang
 * @since 2025/2/23 17:29
 * @version 1.0.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
@ConditionalOnProperty(prefix = PropertiesConstants.LOG, name = PropertiesConstants.ENABLED, havingValue = "true", matchIfMissing = true)
public @interface ConditionalOnEnabledLog {
}