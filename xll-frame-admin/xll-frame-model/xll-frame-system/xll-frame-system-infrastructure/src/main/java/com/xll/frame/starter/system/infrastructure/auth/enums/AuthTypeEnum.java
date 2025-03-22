package com.xll.frame.starter.system.infrastructure.auth.enums;

import com.xll.frame.starter.common.constant.UiConstants;
import com.xll.frame.starter.core.enums.BaseEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
/**
 * 功能描述: <br>
 * <p>
 *  <认证类型枚举>
 * </p>
 * @author xuliangliang
 * @since 2025/2/23 18:05
 * @version 1.0.0
 */
@Getter
@RequiredArgsConstructor
public enum AuthTypeEnum implements BaseEnum<String> {

    /**
     * 账号
     */
    ACCOUNT("ACCOUNT", "账号", UiConstants.COLOR_SUCCESS),

    /**
     * 邮箱
     */
    EMAIL("EMAIL", "邮箱", UiConstants.COLOR_PRIMARY),

    /**
     * 手机号
     */
    PHONE("PHONE", "手机号", UiConstants.COLOR_PRIMARY),

    /**
     * 第三方账号
     */
    SOCIAL("SOCIAL", "第三方账号", UiConstants.COLOR_ERROR);

    private final String value;
    private final String description;
    private final String color;
}
