package com.xll.frame.starter.log.aop.autoconfigure;

import com.xll.frame.starter.log.aop.aspect.AccessLogAspect;
import com.xll.frame.starter.log.aop.aspect.LogAspect;
import com.xll.frame.starter.log.aop.handler.AopLogHandler;
import com.xll.frame.starter.log.core.LogFilter;
import com.xll.frame.starter.log.core.LogHandler;
import com.xll.frame.starter.log.core.annotation.ConditionalOnEnabledLog;
import com.xll.frame.starter.log.core.dao.LogDao;
import com.xll.frame.starter.log.core.dao.impl.DefaultLogDaoImpl;
import com.xll.frame.starter.log.core.model.LogProperties;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 功能描述: <br>
 * <p>
 *  <日志自动配置>
 * </p>
 * @author xuliangliang
 * @since 2025/2/23 17:43
 * @version 1.0.0
 */
@Slf4j
@Configuration
@ConditionalOnEnabledLog
@EnableConfigurationProperties(LogProperties.class)
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
public class LogAopAutoConfiguration {

    private final LogProperties logProperties;

    public LogAopAutoConfiguration(LogProperties logProperties) {
        this.logProperties = logProperties;
    }

    /**
     * 日志过滤器
     */
    @Bean
    @ConditionalOnMissingBean
    public LogFilter logFilter() {
        return new LogFilter(logProperties);
    }

    /**
     * 日志切面
     *
     * @param logHandler 日志处理器
     * @param logDao     日志持久层接口
     * @return {@link LogAspect }
     */
    @Bean
    @ConditionalOnMissingBean
    public LogAspect logAspect(LogHandler logHandler, LogDao logDao) {
        return new LogAspect(logProperties, logHandler, logDao);
    }

    /**
     * 访问日志切面
     *
     * @return {@link LogAspect }
     */
    @Bean
    @ConditionalOnMissingBean
    public AccessLogAspect accessLogAspect() {
        return new AccessLogAspect(logProperties);
    }

    /**
     * 日志处理器
     */
    @Bean
    @ConditionalOnMissingBean
    public LogHandler logHandler() {
        return new AopLogHandler();
    }

    /**
     * 日志持久层接口
     */
    @Bean
    @ConditionalOnMissingBean
    public LogDao logDao() {
        return new DefaultLogDaoImpl();
    }

    @PostConstruct
    public void postConstruct() {
        log.debug("[Frame Starter] - Auto Configuration 'Log-AOP' completed initialization.");
    }
}
