package com.xll.frame.starter.mns.websocket.core;

import org.springframework.http.server.ServletServerHttpRequest;

/**
 * 功能描述: <br>
 * <p>
 *  <WebSocket 客户端服务>
 * </p>
 * @author xuliangliang
 * @since 2025/2/23 16:42
 * @version 1.0.0
 */
public interface WebSocketClientService {

    /**
     * 获取当前客户端 ID
     *
     * @param request 请求对象
     * @return 当前客户端 ID
     */
    String getClientId(ServletServerHttpRequest request);
}
