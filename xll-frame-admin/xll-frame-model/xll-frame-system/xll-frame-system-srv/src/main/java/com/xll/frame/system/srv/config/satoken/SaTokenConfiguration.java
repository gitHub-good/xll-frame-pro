package com.xll.frame.system.srv.config.satoken;

import cn.dev33.satoken.SaManager;
import cn.dev33.satoken.context.SaHolder;
import cn.dev33.satoken.context.model.SaRequest;
import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.sign.SaSignTemplate;
import cn.dev33.satoken.sign.SaSignUtil;
import cn.dev33.satoken.stp.StpInterface;
import cn.dev33.satoken.stp.StpUtil;
import com.xll.frame.starter.auth.satoken.autoconfigure.SaTokenExtensionProperties;
import com.xll.frame.starter.common.context.UserContext;
import com.xll.frame.starter.common.context.UserContextHolder;
import com.xll.frame.starter.core.constant.StringConstants;
import com.xll.frame.starter.core.exception.BusinessException;
import com.xll.frame.starter.core.validation.CheckUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * 功能描述: <br>
 * <p>
 *  <Sa-Token 配置>
 * </p>
 * @author xuliangliang
 * @since 2025/3/23 01:28
 * @version 1.0.0
 */
@Configuration
@RequiredArgsConstructor
public class SaTokenConfiguration {

    private final SaTokenExtensionProperties properties;
    private final LoginPasswordProperties loginPasswordProperties;
    /**
     * Sa-Token 权限认证配置
     */
    @Bean
    public StpInterface stpInterface() {
        return new SaTokenPermissionImpl();
    }

    /**
     * SaToken 拦截器配置
     */
    @Bean
    public SaInterceptor saInterceptor() {
        return new SaExtensionInterceptor(handle -> SaRouter.match(StringConstants.PATH_PATTERN)
            .notMatch(properties.getSecurity().getExcludes())
            .check(r -> {
                // 如果包含 sign，进行 API 接口参数签名验证
                SaRequest saRequest = SaHolder.getRequest();
                List<String> paramNames = saRequest.getParamNames();
                if (paramNames.stream().anyMatch(SaSignTemplate.sign::equals)) {
                    try {
                        SaSignUtil.checkRequest(saRequest);
                    } catch (Exception e) {
                        throw new BusinessException(e.getMessage());
                    }
                    return;
                }
                // 不包含 sign 参数，进行普通登录验证
                StpUtil.checkLogin();
                if (SaRouter.isMatchCurrURI(loginPasswordProperties.getExcludes())) {
                    return;
                }
                UserContext userContext = UserContextHolder.getContext();
                CheckUtils.throwIf(userContext.isPasswordExpired(), "密码已过期，请修改密码");
            }));
    }
}
