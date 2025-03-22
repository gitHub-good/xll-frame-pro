package com.xll.frame.starter.extension.crud.core.autoconfigure;

import com.xll.frame.starter.extension.crud.core.annotation.CrudApi;
import com.xll.frame.starter.extension.crud.core.aop.CrudApiAnnotationAdvisor;
import com.xll.frame.starter.extension.crud.core.aop.CrudApiAnnotationInterceptor;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * 功能描述: <br>
 * <p>
 *  <CRUD REST Controller 自动配置>
 * </p>
 * @author xuliangliang
 * @since 2025/2/16 20:07
 * @version 1.0.0
 */
@AutoConfiguration
@EnableConfigurationProperties(CrudProperties.class)
public class CrudRestControllerAutoConfiguration {

    private static final Logger log = LoggerFactory.getLogger(CrudRestControllerAutoConfiguration.class);

    /**
     * CRUD API 注解通知
     */
    @Bean
    @ConditionalOnMissingBean
    public CrudApiAnnotationAdvisor crudApiAnnotationAdvisor(CrudApiAnnotationInterceptor crudApiAnnotationInterceptor) {
        return new CrudApiAnnotationAdvisor(crudApiAnnotationInterceptor, CrudApi.class);
    }

    /**
     * CRUD API 注解拦截器
     */
    @Bean
    @ConditionalOnMissingBean
    public CrudApiAnnotationInterceptor crudApiAnnotationInterceptor() {
        return new CrudApiAnnotationInterceptor();
    }

    @PostConstruct
    public void postConstruct() {
        log.debug("[ContiNew Starter] - Auto Configuration 'Extension-CRUD REST Controller' completed initialization.");
    }
}
