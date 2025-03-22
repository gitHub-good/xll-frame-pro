package com.xll.frame.starter.mns.websocket.autoconfigure;

import com.xll.frame.starter.core.constant.PropertiesConstants;
import com.xll.frame.starter.core.constant.StringConstants;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 功能描述: <br>
 * <p>
 *  <WebSocket 配置属性>
 * </p>
 * @author xuliangliang
 * @since 2025/2/23 16:41
 * @version 1.0.0
 */
@Data
@ConfigurationProperties(PropertiesConstants.MESSAGING_WEBSOCKET)
public class WebSocketProperties {

    private static final List<String> ALL = Collections.singletonList(StringConstants.ASTERISK);

    /**
     * 是否启用 WebSocket
     */
    private boolean enabled = true;

    /**
     * 路径
     */
    private String path = StringConstants.SLASH + "websocket";

    /**
     * 允许跨域的域名
     */
    private List<String> allowedOrigins = new ArrayList<>(ALL);

    /**
     * 客户端 ID Key
     */
    private String clientIdKey = "CLIENT_ID";
}
