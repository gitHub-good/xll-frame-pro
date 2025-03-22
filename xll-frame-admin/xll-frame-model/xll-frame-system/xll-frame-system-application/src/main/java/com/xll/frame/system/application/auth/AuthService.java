package com.xll.frame.system.application.auth;

import com.xll.frame.system.infrastructure.auth.model.req.LoginReq;
import com.xll.frame.system.infrastructure.auth.model.resp.LoginResp;
import com.xll.frame.system.infrastructure.auth.model.resp.RouteResp;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

/**
 * 功能描述: <br>
 * <p>
 *  <认证业务接口>
 * </p>
 * @author xuliangliang
 * @since 2025/2/23 17:25
 * @version 1.0.0
 */
public interface AuthService {

    /**
     * 登录
     *
     * @param req     登录请求参数
     * @param request 请求对象
     * @return 登录响应参数
     */
    LoginResp login(LoginReq req, HttpServletRequest request);

    /**
     * 构建路由树
     *
     * @param userId 用户 ID
     * @return 路由树
     */
    List<RouteResp> buildRouteTree(Long userId);
}
