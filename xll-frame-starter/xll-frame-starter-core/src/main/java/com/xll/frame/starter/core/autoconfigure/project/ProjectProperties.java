package com.xll.frame.starter.core.autoconfigure.project;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 功能描述: <br>
 * <p>
 *  <项目配置属性>
 * </p>
 * @author xuliangliang
 * @since 2025/2/6 20:20
 * @version 1.0.0
 */
@Data
@ConfigurationProperties("project")
public class ProjectProperties {

    /**
     * 名称
     */
    private String name;

    /**
     * 应用名称
     */
    private String appName;

    /**
     * 版本
     */
    private String version;

    /**
     * 描述
     */
    private String description;

    /**
     * URL
     */
    private String url;

    /**
     * 基本包
     */
    private String basePackage;

    /**
     * 联系人
     */
    private Contact contact;

    /**
     * 许可协议
     */
    private License license;

    /**
     * 是否为生产环境
     */
    private boolean production = false;

    /**
     * 联系人配置属性
     */
    @Data
    public static class Contact {
        /**
         * 名称
         */
        private String name;

        /**
         * 邮箱
         */
        private String email;

        /**
         * URL
         */
        private String url;
    }

    /**
     * 许可协议配置属性
     */
    @Data
    public static class License {
        /**
         * 名称
         */
        private String name;

        /**
         * URL
         */
        private String url;
    }
}
