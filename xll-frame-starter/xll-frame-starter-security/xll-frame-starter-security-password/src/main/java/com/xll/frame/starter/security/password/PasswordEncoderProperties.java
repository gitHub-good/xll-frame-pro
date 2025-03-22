package com.xll.frame.starter.security.password;

import com.xll.frame.starter.core.constant.PropertiesConstants;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
/**
 * 功能描述: <br>
 * <p>
 *  <密码编解码配置属性>
 * </p>
 * @author xuliangliang
 * @since 2025/2/23 15:51
 * @version 1.0.0
 */
@Data
@ConfigurationProperties(PropertiesConstants.SECURITY_PASSWORD)
public class PasswordEncoderProperties {

    /**
     * 是否启用密码编解码配置
     */
    private boolean enabled = true;

    /**
     * 默认启用的编码器 ID（默认：BCryptPasswordEncoder）
     */
    private String encodingId = "bcrypt";
}