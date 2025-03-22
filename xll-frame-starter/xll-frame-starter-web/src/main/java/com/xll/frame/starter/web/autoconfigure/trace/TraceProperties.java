package com.xll.frame.starter.web.autoconfigure.trace;

import com.xll.frame.starter.core.constant.PropertiesConstants;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

/**
 * 功能描述: <br>
 * <p>
 *  <链路跟踪配置属性>
 * </p>
 * @author xuliangliang
 * @since 2025/2/16 18:58
 * @version 1.0.0
 */
@Data
@ConfigurationProperties(PropertiesConstants.WEB_TRACE)
public class TraceProperties {

    /**
     * 是否启用链路跟踪配置
     */
    private boolean enabled = false;

    /**
     * 链路 ID 名称
     */
    private String traceIdName = "traceId";

    /**
     * TLog 配置
     */
    @NestedConfigurationProperty
    private TLogProperties tlog;
}
