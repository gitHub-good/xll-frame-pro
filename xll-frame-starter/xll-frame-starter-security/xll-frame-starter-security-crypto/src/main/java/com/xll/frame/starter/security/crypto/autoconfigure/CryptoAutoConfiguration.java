package com.xll.frame.starter.security.crypto.autoconfigure;

import com.xll.frame.starter.core.constant.PropertiesConstants;
import com.xll.frame.starter.security.crypto.core.MyBatisDecryptInterceptor;
import com.xll.frame.starter.security.crypto.core.MyBatisEncryptInterceptor;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * 功能描述: <br>
 * <p>
 *  <加/解密自动配置>
 * </p>
 * @author xuliangliang
 * @since 2025/2/6 21:59
 * @version 1.0.0
 */
@Slf4j
@AutoConfiguration
@EnableConfigurationProperties(CryptoProperties.class)
@ConditionalOnProperty(prefix = PropertiesConstants.SECURITY_CRYPTO, name = PropertiesConstants.ENABLED, havingValue = "true", matchIfMissing = true)
public class CryptoAutoConfiguration {
    private final CryptoProperties properties;

    public CryptoAutoConfiguration(CryptoProperties properties) {
        this.properties = properties;
    }

    /**
     * MyBatis 加密拦截器配置
     */
    @Bean
    @ConditionalOnMissingBean
    public MyBatisEncryptInterceptor myBatisEncryptInterceptor() {
        return new MyBatisEncryptInterceptor(properties);
    }

    /**
     * MyBatis 解密拦截器配置
     */
    @Bean
    @ConditionalOnMissingBean(MyBatisDecryptInterceptor.class)
    public MyBatisDecryptInterceptor myBatisDecryptInterceptor() {
        return new MyBatisDecryptInterceptor(properties);
    }

    @PostConstruct
    public void postConstruct() {
        log.debug("[ContiNew Starter] - Auto Configuration 'Security-Crypto' completed initialization.");
    }
}
