package com.xll.frame.starter.web.autoconfigure.xss;

import cn.hutool.core.collection.CollUtil;
import com.xll.frame.starter.web.util.SpringWebUtils;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

/**
 * 功能描述: <br>
 * <p>
 *  <XSS 过滤器>
 * </p>
 * @author xuliangliang
 * @since 2025/2/16 18:58
 * @version 1.0.0
 */
public class XssFilter implements Filter {

    private static final Logger log = LoggerFactory.getLogger(XssFilter.class);

    private final XssProperties xssProperties;

    public XssFilter(XssProperties xssProperties) {
        this.xssProperties = xssProperties;
    }

    @Override
    public void init(FilterConfig filterConfig) {
        log.debug("[ContiNew Starter] - Auto Configuration 'Web-XssFilter' completed initialization.");
    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        // 未开启 XSS 过滤，则直接跳过
        if (servletRequest instanceof HttpServletRequest request && xssProperties.isEnabled()) {
            // 放行路由：忽略 XSS 过滤
            List<String> excludePatterns = xssProperties.getExcludePatterns();
            if (CollUtil.isNotEmpty(excludePatterns) && SpringWebUtils.isMatch(request
                .getServletPath(), excludePatterns)) {
                filterChain.doFilter(request, servletResponse);
                return;
            }
            // 拦截路由：执行 XSS 过滤
            List<String> includePatterns = xssProperties.getIncludePatterns();
            if (CollUtil.isNotEmpty(includePatterns)) {
                if (SpringWebUtils.isMatch(request.getServletPath(), includePatterns)) {
                    filterChain.doFilter(new XssServletRequestWrapper(request, xssProperties), servletResponse);
                } else {
                    filterChain.doFilter(request, servletResponse);
                }
                return;
            }
            // 默认：执行 XSS 过滤
            filterChain.doFilter(new XssServletRequestWrapper(request, xssProperties), servletResponse);
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
