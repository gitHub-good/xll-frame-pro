package com.xll.frame.starter.mns.websocket.autoconfigure;

import com.xll.frame.starter.core.constant.PropertiesConstants;
import com.xll.frame.starter.mns.websocket.core.WebCustomSocketHandler;
import com.xll.frame.starter.mns.websocket.core.WebSocketClientService;
import com.xll.frame.starter.mns.websocket.core.WebSocketInterceptor;
import com.xll.frame.starter.mns.websocket.dao.WebSocketSessionDao;
import com.xll.frame.starter.mns.websocket.dao.WebSocketSessionDaoDefaultImpl;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.server.HandshakeInterceptor;

/**
 * 功能描述: <br>
 * <p>
 *  <WebSocket 自动配置>
 * </p>
 * @author xuliangliang
 * @since 2025/2/23 16:40
 * @version 1.0.0
 */
@Slf4j
@AutoConfiguration
@EnableWebSocket
@EnableConfigurationProperties(WebSocketProperties.class)
@ConditionalOnProperty(prefix = PropertiesConstants.MESSAGING_WEBSOCKET, name = PropertiesConstants.ENABLED, havingValue = "true", matchIfMissing = true)
public class WebSocketAutoConfiguration {

    private final WebSocketProperties properties;

    public WebSocketAutoConfiguration(WebSocketProperties properties) {
        this.properties = properties;
    }

    @Bean
    public WebSocketConfigurer webSocketConfigurer(WebSocketHandler handler, HandshakeInterceptor interceptor) {
        return registry -> registry.addHandler(handler, properties.getPath())
            .addInterceptors(interceptor)
            .setAllowedOrigins(properties.getAllowedOrigins().toArray(String[]::new));
    }

    @Bean
    @ConditionalOnMissingBean
    public WebSocketHandler webSocketHandler(WebSocketSessionDao webSocketSessionDao) {
        return new WebCustomSocketHandler(properties, webSocketSessionDao);
    }

    @Bean
    @ConditionalOnMissingBean
    public HandshakeInterceptor handshakeInterceptor(WebSocketClientService webSocketClientService) {
        return new WebSocketInterceptor(properties, webSocketClientService);
    }

    /**
     * WebSocket 会话 DAO
     */
    @Bean
    @ConditionalOnMissingBean
    public WebSocketSessionDao webSocketSessionDao() {
        return new WebSocketSessionDaoDefaultImpl();
    }

    /**
     * WebSocket 客户端服务（如不提供，则报错）
     */
    @Bean
    @ConditionalOnMissingBean
    public WebSocketClientService webSocketClientService() {
        throw new NoSuchBeanDefinitionException(WebSocketClientService.class);
    }

    @PostConstruct
    public void postConstruct() {
        log.debug("[Frame Starter] - Auto Configuration 'Mns-WebSocket' completed initialization.");
    }
}
