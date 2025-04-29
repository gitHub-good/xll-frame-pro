package com.xll.frame.system.srv.controller.auth;

import cn.dev33.satoken.annotation.SaIgnore;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import com.xll.frame.starter.common.context.UserContext;
import com.xll.frame.starter.common.context.UserContextHolder;
import com.xll.frame.starter.log.core.annotation.Log;
import com.xll.frame.system.application.auth.AuthService;
import com.xll.frame.system.infrastructure.service.UserService;
import com.xll.frame.system.infrastructure.auth.model.req.LoginReq;
import com.xll.frame.system.infrastructure.auth.model.resp.LoginResp;
import com.xll.frame.system.infrastructure.auth.model.resp.RouteResp;
import com.xll.frame.system.infrastructure.auth.model.resp.UserInfoResp;
import com.xll.frame.system.infrastructure.model.resp.user.UserDetailResp;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 功能描述: <br>
 * <p>
 *  <认证 API>
 * </p>
 * @author xuliangliang
 * @since 2025/3/23 02:28
 * @version 1.0.0
 */
@Tag(name = "认证 API")
@Log(module = "登录")
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    private final UserService userService;
    @SaIgnore
    @Operation(summary = "登录", description = "用户登录")
    @PostMapping("/login")
    public LoginResp login(@Validated @RequestBody LoginReq req, HttpServletRequest request) {
        return authService.login(req, request);
    }

    @Operation(summary = "登出", description = "注销用户的当前登录")
    @Parameter(name = "Authorization", description = "令牌", required = true, example = "Bearer xxxx-xxxx-xxxx-xxxx", in = ParameterIn.HEADER)
    @PostMapping("/logout")
    public Object logout() {
        Object loginId = StpUtil.getLoginId(-1L);
        StpUtil.logout();
        return loginId;
    }

    @Log(ignore = true)
    @Operation(summary = "获取用户信息", description = "获取登录用户信息")
    @GetMapping("/user/info")
    public UserInfoResp getUserInfo() {
        UserContext userContext = UserContextHolder.getContext();
        UserDetailResp userDetailResp = userService.get(userContext.getId());
        UserInfoResp userInfoResp = BeanUtil.copyProperties(userDetailResp, UserInfoResp.class);
        userInfoResp.setPermissions(userContext.getPermissions());
        userInfoResp.setRoles(userContext.getRoleCodes());
        userInfoResp.setPwdExpired(userContext.isPasswordExpired());
        return userInfoResp;
    }

    @Log(ignore = true)
    @Operation(summary = "获取路由信息", description = "获取登录用户的路由信息")
    @GetMapping("/user/route")
    public List<RouteResp> listRoute() {
        return authService.buildRouteTree(UserContextHolder.getUserId());
    }

}
