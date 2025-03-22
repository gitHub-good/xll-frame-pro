package com.xll.frame.starter.web.autoconfigure.response;

import com.feiniaojin.gracefulresponse.GracefulResponseProperties;
import com.xll.frame.starter.core.constant.PropertiesConstants;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 功能描述: <br>
 * <p>
 *  <全局响应配置属性>
 * </p>
 * @author xuliangliang
 * @since 2025/2/16 18:56
 * @version 1.0.0
 */
@ConfigurationProperties(PropertiesConstants.WEB_RESPONSE)
public class GlobalResponseProperties extends GracefulResponseProperties {}
