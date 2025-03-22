package com.xll.frame.starter.mns.websocket.util;

import cn.hutool.extra.spring.SpringUtil;
import com.xll.frame.starter.mns.websocket.dao.WebSocketSessionDao;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;

/**
 * 功能描述: <br>
 * <p>
 *  <WebSocket 工具类>
 * </p>
 * @author xuliangliang
 * @since 2025/2/23 16:44
 * @version 1.0.0
 */
@Slf4j
public class WebSocketUtils {

    private static final WebSocketSessionDao SESSION_DAO = SpringUtil.getBean(WebSocketSessionDao.class);

    private WebSocketUtils() {
    }

    /**
     * 发送消息
     *
     * @param clientId 客户端 ID
     * @param message  消息内容
     */
    public static void sendMessage(String clientId, String message) {
        WebSocketSession session = SESSION_DAO.get(clientId);
        sendMessage(session, message);
    }

    /**
     * 发送消息
     *
     * @param session 会话
     * @param message 消息内容
     */
    public static void sendMessage(WebSocketSession session, String message) {
        sendMessage(session, new TextMessage(message));
    }

    /**
     * 发送消息
     *
     * @param session 会话
     * @param message 消息内容
     */
    public static void sendMessage(WebSocketSession session, WebSocketMessage<?> message) {
        if (session == null || !session.isOpen()) {
            log.warn("WebSocket session closed.");
            return;
        }
        try {
            session.sendMessage(message);
        } catch (IOException e) {
            log.error("WebSocket send message failed. sessionId: {}.", session.getId(), e);
        }
    }
}
