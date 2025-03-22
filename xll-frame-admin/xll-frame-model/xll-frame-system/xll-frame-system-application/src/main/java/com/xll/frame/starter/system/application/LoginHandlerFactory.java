package com.xll.frame.starter.system.application;

import com.xll.frame.starter.system.infrastructure.auth.LoginHandler;
import com.xll.frame.starter.system.infrastructure.auth.enums.AuthTypeEnum;
import com.xll.frame.starter.system.infrastructure.auth.model.req.LoginReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

/**
 * 功能描述: <br>
 * <p>
 *  <登录处理器工厂>
 * </p>
 * @author xuliangliang
 * @since 2025/2/23 18:05
 * @version 1.0.0
 */
@Component
public class LoginHandlerFactory {

    private final Map<AuthTypeEnum, LoginHandler<? extends LoginReq>> handlerMap = new EnumMap<>(AuthTypeEnum.class);

    @Autowired
    public LoginHandlerFactory(List<LoginHandler<? extends LoginReq>> handlers) {
        for (LoginHandler<? extends LoginReq> handler : handlers) {
            handlerMap.put(handler.getAuthType(), handler);
        }
    }

    /**
     * 根据认证类型获取
     *
     * @param authType 认证类型
     * @return 认证处理器
     */
    public LoginHandler<LoginReq> getHandler(AuthTypeEnum authType) {
        return (LoginHandler<LoginReq>)handlerMap.get(authType);
    }
}
