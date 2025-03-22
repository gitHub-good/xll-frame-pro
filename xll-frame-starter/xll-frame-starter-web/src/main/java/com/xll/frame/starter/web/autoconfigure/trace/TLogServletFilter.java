package com.xll.frame.starter.web.autoconfigure.trace;

import cn.hutool.core.text.CharSequenceUtil;
import com.yomahub.tlog.context.TLogContext;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * 功能描述: <br>
 * <p>
 *  <TLog 过滤器>
 * </p>
 * @author xuliangliang
 * @since 2025/2/16 18:57
 * @version 1.0.0
 */
public class TLogServletFilter implements Filter {

    private final TraceProperties traceProperties;

    public TLogServletFilter(TraceProperties traceProperties) {
        this.traceProperties = traceProperties;
    }

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        if (request instanceof HttpServletRequest httpServletRequest && response instanceof HttpServletResponse httpServletResponse) {
            try {
                TLogWebCommon.loadInstance().preHandle(httpServletRequest);
                // 把 traceId 放入 response 的 header，为了方便有些人有这样的需求，从前端拿整条链路的 traceId
                String traceIdName = traceProperties.getTraceIdName();
                if (CharSequenceUtil.isNotBlank(traceIdName)) {
                    httpServletResponse.addHeader(traceIdName, TLogContext.getTraceId());
                }
                chain.doFilter(request, response);
            } finally {
                TLogWebCommon.loadInstance().afterCompletion();
            }
            return;
        }
        chain.doFilter(request, response);
    }
}
