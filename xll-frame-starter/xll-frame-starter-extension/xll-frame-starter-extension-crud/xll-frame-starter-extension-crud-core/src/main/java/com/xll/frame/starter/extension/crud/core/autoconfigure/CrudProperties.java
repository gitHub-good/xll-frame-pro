package com.xll.frame.starter.extension.crud.core.autoconfigure;

import com.xll.frame.starter.core.constant.PropertiesConstants;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

/**
 * 功能描述: <br>
 * <p>
 *  <CRUD 配置属性>
 * </p>
 * @author xuliangliang
 * @since 2025/2/16 20:06
 * @version 1.0.0
 */
@Data
@ConfigurationProperties(PropertiesConstants.CRUD)
public class CrudProperties {

    /**
     * 树配置
     */
    @NestedConfigurationProperty
    private CrudTreeProperties tree = new CrudTreeProperties();

}
