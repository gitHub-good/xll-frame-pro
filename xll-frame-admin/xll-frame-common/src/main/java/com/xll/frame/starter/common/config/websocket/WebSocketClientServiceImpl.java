package com.xll.frame.starter.common.config.websocket;

import cn.dev33.satoken.stp.StpUtil;
import com.xll.frame.starter.core.exception.BusinessException;
import com.xll.frame.starter.mns.websocket.core.WebSocketClientService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;

/**
 * 功能描述: <br>
 * <p>
 *  <当前登录用户 Provider>
 * </p>
 * @author xuliangliang
 * @since 2025/2/23 16:46
 * @version 1.0.0
 */
@Component
public class WebSocketClientServiceImpl implements WebSocketClientService {

    @Override
    public String getClientId(ServletServerHttpRequest request) {
        HttpServletRequest servletRequest = request.getServletRequest();
        String token = servletRequest.getParameter("token");
        if (null == StpUtil.getLoginIdByToken(token)) {
            throw new BusinessException("登录已过期，请重新登录");
        }
        return token;
    }
}
