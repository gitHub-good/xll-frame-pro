package com.xll.frame.starter.common.config.properties;

import cn.hutool.extra.spring.SpringUtil;

/**
 * 功能描述: <br>
 * <p>
 *  <RSA 配置属性>
 * </p>
 * @author xuliangliang
 * @since 2025/2/23 16:37
 * @version 1.0.0
 */
public class RsaProperties {

    /**
     * 私钥
     */
    public static final String PRIVATE_KEY;
    public static final String PUBLIC_KEY;

    static {
        PRIVATE_KEY = SpringUtil.getProperty("frame-starter.security.crypto.private-key");
        PUBLIC_KEY = SpringUtil.getProperty("frame-starter.security.crypto.public-key");
    }

    private RsaProperties() {
    }
}
