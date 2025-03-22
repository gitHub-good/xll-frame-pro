package com.xll.frame.starter.auth.satoken.autoconfigure;

import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.dev33.satoken.jwt.StpLogicJwtForSimple;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpLogic;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.xll.frame.starter.auth.satoken.autoconfigure.dao.SaTokenDaoConfiguration;
import com.xll.frame.starter.core.constant.PropertiesConstants;
import com.xll.frame.starter.core.constant.StringConstants;
import com.xll.frame.starter.core.util.GeneralPropertySourceFactory;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 功能描述: <br>
 * <p>
 *  <Sa-Token 自动配置>
 * </p>
 * @author xuliangliang
 * @since 2025/2/23 16:15
 * @version 1.0.0
 */
@AutoConfiguration
@EnableConfigurationProperties(SaTokenExtensionProperties.class)
@ConditionalOnProperty(prefix = "sa-token.extension", name = PropertiesConstants.ENABLED, havingValue = "true")
@PropertySource(value = "classpath:default-auth-satoken.yml", factory = GeneralPropertySourceFactory.class)
public class SaTokenAutoConfiguration implements WebMvcConfigurer {

    private static final Logger log = LoggerFactory.getLogger(SaTokenAutoConfiguration.class);
    private final SaTokenExtensionProperties properties;

    public SaTokenAutoConfiguration(SaTokenExtensionProperties properties) {
        this.properties = properties;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(SpringUtil.getBean(SaInterceptor.class)).addPathPatterns(StringConstants.PATH_PATTERN);
    }

    /**
     * SaToken 拦截器配置
     */
    @Bean
    @ConditionalOnMissingBean
    public SaInterceptor saInterceptor() {
        return new SaInterceptor(handle -> SaRouter.match(StringConstants.PATH_PATTERN)
            .notMatch(properties.getSecurity().getExcludes())
            .check(r -> StpUtil.checkLogin()));
    }

    /**
     * 持久层配置
     */
    @Configuration
    @Import({SaTokenDaoConfiguration.Default.class, SaTokenDaoConfiguration.Redis.class,
        SaTokenDaoConfiguration.Custom.class})
    protected static class SaTokenDaoAutoConfiguration {}

    /**
     * 整合 JWT（简单模式）
     */
    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "sa-token.extension", name = "enableJwt", havingValue = "true")
    public StpLogic stpLogic() {
        return new StpLogicJwtForSimple();
    }

    @PostConstruct
    public void postConstruct() {
        log.debug("[ContiNew Starter] - Auto Configuration 'SaToken' completed initialization.");
    }
}
