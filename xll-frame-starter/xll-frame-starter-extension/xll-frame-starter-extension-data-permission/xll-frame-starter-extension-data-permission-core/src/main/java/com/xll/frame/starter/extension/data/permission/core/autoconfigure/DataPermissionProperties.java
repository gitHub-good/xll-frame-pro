package com.xll.frame.starter.extension.data.permission.core.autoconfigure;

import com.xll.frame.starter.core.constant.PropertiesConstants;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 数据权限配置属性
 *
 * @author Charles7c
 * @since 2.7.0
 */
@Data
@ConfigurationProperties(PropertiesConstants.DATA_PERMISSION)
public class DataPermissionProperties {

    /**
     * 是否启用多租户
     */
    private boolean enabled = true;
}
