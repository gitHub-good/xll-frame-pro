package com.xll.frame.starter.security.crypto.autoconfigure;

import com.xll.frame.starter.core.constant.PropertiesConstants;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 功能描述: <br>
 * <p>
 *  <加/解密配置属性>
 * </p>
 * @author xuliangliang
 * @since 2025/2/6 22:07
 * @version 1.0.0
 */
@Data
@ConfigurationProperties(PropertiesConstants.SECURITY_CRYPTO)
public class CryptoProperties {

    /**
     * 是否启用加/解密配置
     */
    private boolean enabled = true;

    /**
     * 对称加密算法密钥
     */
    private String password;

    /**
     * 非对称加密算法公钥
     */
    private String publicKey;

    /**
     * 非对称加密算法私钥
     */
    private String privateKey;
}