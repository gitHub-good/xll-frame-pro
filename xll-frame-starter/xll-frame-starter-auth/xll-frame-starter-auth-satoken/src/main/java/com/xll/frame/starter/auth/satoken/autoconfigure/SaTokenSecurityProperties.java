package com.xll.frame.starter.auth.satoken.autoconfigure;

import lombok.Data;

/**
 * 功能描述: <br>
 * <p>
 *  <SaToken 安全配置属性>
 * </p>
 * @author xuliangliang
 * @since 2025/2/23 16:18
 * @version 1.0.0
 */
@Data
public class SaTokenSecurityProperties {

    /**
     * 排除（放行）路径配置
     */
    private String[] excludes = new String[0];
}