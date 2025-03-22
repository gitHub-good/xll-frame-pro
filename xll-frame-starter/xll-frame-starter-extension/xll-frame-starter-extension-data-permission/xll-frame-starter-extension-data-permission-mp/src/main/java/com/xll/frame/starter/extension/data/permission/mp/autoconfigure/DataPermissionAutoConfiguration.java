package com.xll.frame.starter.extension.data.permission.mp.autoconfigure;

import com.baomidou.mybatisplus.extension.plugins.handler.DataPermissionHandler;
import com.baomidou.mybatisplus.extension.plugins.inner.DataPermissionInterceptor;
import com.xll.frame.starter.core.constant.PropertiesConstants;
import com.xll.frame.starter.extension.data.permission.core.autoconfigure.DataPermissionProperties;
import com.xll.frame.starter.extension.data.permission.core.filter.DataPermissionUserContextProvider;
import com.xll.frame.starter.extension.data.permission.mp.handler.DefaultDataPermissionHandler;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ResolvableType;

/**
 * 功能描述: <br>
 * <p>
 *  <数据权限自动配置>
 * </p>
 * @author xuliangliang
 * @since 2025/2/23 16:31
 * @version 1.0.0
 */
@Slf4j
@AutoConfiguration
@EnableConfigurationProperties(DataPermissionProperties.class)
@ConditionalOnProperty(prefix = PropertiesConstants.DATA_PERMISSION, name = PropertiesConstants.ENABLED, havingValue = "true", matchIfMissing = true)
public class DataPermissionAutoConfiguration {

    private DataPermissionAutoConfiguration() {
    }

    /**
     * 数据权限拦截器
     */
    @Bean
    @ConditionalOnMissingBean
    public DataPermissionInterceptor dataPermissionInterceptor(DataPermissionHandler dataPermissionHandler) {
        return new DataPermissionInterceptor(dataPermissionHandler);
    }

    /**
     * 数据权限处理器
     */
    @Bean
    @ConditionalOnMissingBean
    public DataPermissionHandler dataPermissionHandler(DataPermissionUserContextProvider dataPermissionUserContextProvider) {
        return new DefaultDataPermissionHandler(dataPermissionUserContextProvider);
    }

    /**
     * 数据权限用户上下文提供者
     */
    @Bean
    @ConditionalOnMissingBean
    public DataPermissionUserContextProvider dataPermissionUserContextProvider() {
        if (log.isErrorEnabled()) {
            log.error("Consider defining a bean of type '{}' in your configuration.", ResolvableType
                .forClass(DataPermissionUserContextProvider.class));
        }
        throw new NoSuchBeanDefinitionException(DataPermissionUserContextProvider.class);
    }

    static {
        log.debug("[Frame Starter] - Auto Configuration 'DataPermission' completed initialization.");
    }
}
