package com.xll.frame.system.srv.config.satoken;

import cn.dev33.satoken.fun.SaParamFunction;
import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.dev33.satoken.stp.StpUtil;
import com.xll.frame.starter.common.context.UserContextHolder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.Nullable;

/**
 * 功能描述: <br>
 * <p>
 *  <Sa-Token 扩展拦截器>
 * </p>
 * @author xuliangliang
 * @since 2025/3/23 01:28
 * @version 1.0.0
 */
public class SaExtensionInterceptor extends SaInterceptor {

    public SaExtensionInterceptor(SaParamFunction<Object> auth) {
        super(auth);
    }

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        boolean flag = super.preHandle(request, response, handler);
        if (flag && StpUtil.isLogin()) {
            UserContextHolder.getContext();
            UserContextHolder.getExtraContext();
        }
        return flag;
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response,
                                Object handler,
                                @Nullable Exception e) throws Exception {
        try {
            super.afterCompletion(request, response, handler, e);
        } finally {
            UserContextHolder.clearContext();
        }
    }
}
