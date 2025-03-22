package com.xll.frame.starter.auth.satoken.autoconfigure;

import com.xll.frame.starter.auth.satoken.autoconfigure.dao.SaTokenDaoProperties;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

/**
 * 功能描述: <br>
 * <p>
 *  <SaToken 扩展配置属性>
 * </p>
 * @author xuliangliang
 * @since 2025/2/23 16:16
 * @version 1.0.0
 */
@Data
@ConfigurationProperties("sa-token.extension")
public class SaTokenExtensionProperties {

    /**
     * 是否启用扩展
     */
    private boolean enabled = false;

    /**
     * 启用 JWT
     */
    private boolean enableJwt = false;

    /**
     * 持久层配置
     */
    @NestedConfigurationProperty
    private SaTokenDaoProperties dao;

    /**
     * 安全配置
     */
    @NestedConfigurationProperty
    private SaTokenSecurityProperties security;
}
