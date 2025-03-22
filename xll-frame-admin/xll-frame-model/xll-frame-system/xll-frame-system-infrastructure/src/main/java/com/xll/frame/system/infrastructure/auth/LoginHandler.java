package com.xll.frame.system.infrastructure.auth;

import com.xll.frame.system.infrastructure.auth.enums.AuthTypeEnum;
import com.xll.frame.system.infrastructure.auth.model.req.LoginReq;
import com.xll.frame.system.infrastructure.auth.model.resp.LoginResp;
import com.xll.frame.system.infrastructure.model.resp.ClientResp;
import jakarta.servlet.http.HttpServletRequest;

/**
 * 功能描述: <br>
 * <p>
 *  <登录处理器>
 * </p>
 * @author xuliangliang
 * @since 2025/2/23 18:07
 * @version 1.0.0
 */
public interface LoginHandler<T extends LoginReq> {

    /**
     * 登录
     *
     * @param req     登录请求参数
     * @param client  客户端信息
     * @param request 请求对象
     * @return 登录响应参数
     */
    LoginResp login(T req, ClientResp client, HttpServletRequest request);

    /**
     * 登录前置处理
     *
     * @param req     登录请求参数
     * @param client  客户端信息
     * @param request 请求对象
     */
    void preLogin(T req, ClientResp client, HttpServletRequest request);

    /**
     * 登录后置处理
     *
     * @param req     登录请求参数
     * @param client  客户端信息
     * @param request 请求对象
     */
    void postLogin(T req, ClientResp client, HttpServletRequest request);

    /**
     * 获取认证类型
     *
     * @return 认证类型
     */
    AuthTypeEnum getAuthType();
}