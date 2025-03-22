package com.xll.frame.starter.web.autoconfigure.xss;

import com.xll.frame.starter.core.constant.PropertiesConstants;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

/**
 * 功能描述: <br>
 * <p>
 *  <XSS 过滤自动配置>
 * </p>
 * @author xuliangliang
 * @since 2025/2/16 18:58
 * @version 1.0.0
 */
@AutoConfiguration
@ConditionalOnWebApplication
@EnableConfigurationProperties(XssProperties.class)
@ConditionalOnProperty(prefix = PropertiesConstants.WEB_XSS, name = PropertiesConstants.ENABLED, havingValue = "true")
public class XssAutoConfiguration {

    /**
     * XSS 过滤器配置
     */
    @Bean
    public FilterRegistrationBean<XssFilter> xssFilter(XssProperties xssProperties) {
        FilterRegistrationBean<XssFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new XssFilter(xssProperties));
        return registrationBean;
    }
}
