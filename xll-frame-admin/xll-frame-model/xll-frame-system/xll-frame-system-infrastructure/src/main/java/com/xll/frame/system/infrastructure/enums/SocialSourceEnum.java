package com.xll.frame.system.infrastructure.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 功能描述: <br>
 * <p>
 *  <第三方账号平台枚举>
 * </p>
 * @author xuliangliang
 * @since 2025/2/23 18:11
 * @version 1.0.0
 */
@Getter
@RequiredArgsConstructor
public enum SocialSourceEnum {

    /**
     * 码云
     */
    GITEE("码云"),

    /**
     * GitHub
     */
    GITHUB("GitHub"),;

    private final String description;
}
