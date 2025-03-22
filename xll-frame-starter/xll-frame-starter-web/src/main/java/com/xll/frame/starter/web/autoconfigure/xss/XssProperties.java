package com.xll.frame.starter.web.autoconfigure.xss;

import com.xll.frame.starter.core.constant.PropertiesConstants;
import com.xll.frame.starter.web.enums.XssMode;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能描述: <br>
 * <p>
 *  <XSS 过滤配置属性>
 * </p>
 * @author xuliangliang
 * @since 2025/2/16 18:58
 * @version 1.0.0
 */
@Data
@ConfigurationProperties(PropertiesConstants.WEB_XSS)
public class XssProperties {

    /**
     * 是否启用 XSS 过滤
     */
    private boolean enabled = true;

    /**
     * 拦截路由（默认为空）
     *
     * <p>
     * 当拦截的路由配置不为空，则根据该配置执行过滤
     * </p>
     */
    private List<String> includePatterns = new ArrayList<>();

    /**
     * 放行路由（默认为空）
     */
    private List<String> excludePatterns = new ArrayList<>();

    /**
     * XSS 模式
     */
    private XssMode mode = XssMode.CLEAN;
}
