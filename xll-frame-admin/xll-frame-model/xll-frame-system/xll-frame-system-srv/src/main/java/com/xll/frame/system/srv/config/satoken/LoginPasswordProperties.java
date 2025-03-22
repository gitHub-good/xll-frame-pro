package com.xll.frame.system.srv.config.satoken;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 功能描述: <br>
 * <p>
 *  <密码配置属性>
 * </p>
 * @author xuliangliang
 * @since 2025/3/23 01:28
 * @version 1.0.0
 */
@Data
@Component
@ConfigurationProperties(prefix = "auth.password")
public class LoginPasswordProperties {

    /**
     * 排除（放行）路径配置
     */
    private String[] excludes = new String[0];
}