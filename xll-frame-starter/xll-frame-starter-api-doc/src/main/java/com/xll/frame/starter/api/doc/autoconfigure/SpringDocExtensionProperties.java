package com.xll.frame.starter.api.doc.autoconfigure;

import io.swagger.v3.oas.models.Components;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

/**
 * 功能描述: <br>
 * <p>
 *  <API 文档扩展配置属性>
 * </p>
 * @author xuliangliang
 * @since 2025/2/16 18:53
 * @version 1.0.0
 */
@ConfigurationProperties("springdoc")
public class SpringDocExtensionProperties {

    /**
     * 组件配置（包括鉴权配置等）
     */
    @NestedConfigurationProperty
    private Components components;

    public Components getComponents() {
        return components;
    }

    public void setComponents(Components components) {
        this.components = components;
    }
}
