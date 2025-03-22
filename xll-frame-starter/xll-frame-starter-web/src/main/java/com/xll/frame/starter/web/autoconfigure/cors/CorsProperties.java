package com.xll.frame.starter.web.autoconfigure.cors;

import com.xll.frame.starter.core.constant.PropertiesConstants;
import com.xll.frame.starter.core.constant.StringConstants;
import lombok.Data;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 功能描述: <br>
 * <p>
 *  <跨域配置属性>
 * </p>
 * @author xuliangliang
 * @since 2025/2/16 18:42
 * @version 1.0.0
 */
@Data
@ConfigurationProperties(PropertiesConstants.WEB_CORS)
public class CorsProperties {

    private static final List<String> ALL = Collections.singletonList(StringConstants.ASTERISK);

    /**
     * 是否启用跨域配置
     */
    private boolean enabled = false;

    /**
     * 允许跨域的域名
     */
    private List<String> allowedOrigins = new ArrayList<>(ALL);

    /**
     * 允许跨域的请求方式
     */
    private List<String> allowedMethods = new ArrayList<>(ALL);

    /**
     * 允许跨域的请求头
     */
    private List<String> allowedHeaders = new ArrayList<>(ALL);

    /**
     * 允许跨域的响应头
     */
    private List<String> exposedHeaders = new ArrayList<>();
}
