package com.xll.frame.starter.captcha.graphic.autoconfigure;

import com.xll.frame.starter.captcha.graphic.core.GraphicCaptchaService;
import com.xll.frame.starter.core.constant.PropertiesConstants;
import jakarta.annotation.PostConstruct;
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
 *  <图形验证码自动配置>
 * </p>
 * @author xuliangliang
 * @since 2025/3/23 01:43
 * @version 1.0.0
 */
@AutoConfiguration
@EnableConfigurationProperties(GraphicCaptchaProperties.class)
@ConditionalOnProperty(prefix = PropertiesConstants.CAPTCHA_GRAPHIC, name = PropertiesConstants.ENABLED, havingValue = "true", matchIfMissing = true)
public class GraphicCaptchaAutoConfiguration {

    private static final Logger log = LoggerFactory.getLogger(GraphicCaptchaAutoConfiguration.class);

    /**
     * 验证码服务接口配置
     */
    @Bean
    @ConditionalOnMissingBean
    public GraphicCaptchaService graphicCaptchaService(GraphicCaptchaProperties properties) {
        return new GraphicCaptchaService(properties);
    }

    @PostConstruct
    public void postConstruct() {
        log.debug("[Frame Starter] - Auto Configuration 'Captcha-Graphic' completed initialization.");
    }
}
