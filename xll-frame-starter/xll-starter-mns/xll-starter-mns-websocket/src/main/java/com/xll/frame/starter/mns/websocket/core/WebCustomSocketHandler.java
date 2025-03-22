package com.xll.frame.starter.mns.websocket.core;

import cn.hutool.core.convert.Convert;
import com.xll.frame.starter.mns.websocket.autoconfigure.WebSocketProperties;
import com.xll.frame.starter.mns.websocket.dao.WebSocketSessionDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;

/**
 * 功能描述: <br>
 * <p>
 *  <WebSocket 处理器>
 * </p>
 * @author xuliangliang
 * @since 2025/2/23 16:42
 * @version 1.0.0
 */
@Slf4j
public class WebCustomSocketHandler extends TextWebSocketHandler {

    private final WebSocketProperties webSocketProperties;
    private final WebSocketSessionDao webSocketSessionDao;

    public WebCustomSocketHandler(WebSocketProperties webSocketProperties, WebSocketSessionDao webSocketSessionDao) {
        this.webSocketProperties = webSocketProperties;
        this.webSocketSessionDao = webSocketSessionDao;
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String clientId = this.getClientId(session);
        log.info("WebSocket receive message. clientId: {}, message: {}.", clientId, message.getPayload());
        super.handleTextMessage(session, message);
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        String clientId = this.getClientId(session);
        webSocketSessionDao.add(clientId, session);
        log.info("WebSocket client connect successfully. clientId: {}.", clientId);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        String clientId = this.getClientId(session);
        webSocketSessionDao.delete(clientId);
        log.info("WebSocket client connect closed. clientId: {}.", clientId);
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws IOException {
        String clientId = this.getClientId(session);
        if (session.isOpen()) {
            session.close();
        }
        webSocketSessionDao.delete(clientId);
    }

    /**
     * 获取客户端 ID
     *
     * @param session 会话
     * @return 客户端 ID
     */
    private String getClientId(WebSocketSession session) {
        return Convert.toStr(session.getAttributes().get(webSocketProperties.getClientIdKey()));
    }
}
