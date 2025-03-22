package com.xll.frame.starter.common.enums;

import com.xll.frame.starter.common.constant.UiConstants;
import com.xll.frame.starter.core.enums.BaseEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 功能描述: <br>
 * <p>
 *  <启用/禁用状态枚举>
 * </p>
 * @author xuliangliang
 * @since 2025/2/23 16:48
 * @version 1.0.0
 */
@Getter
@RequiredArgsConstructor
public enum DisEnableStatusEnum implements BaseEnum<Integer> {

    /**
     * 启用
     */
    ENABLE(1, "启用", UiConstants.COLOR_SUCCESS),

    /**
     * 禁用
     */
    DISABLE(2, "禁用", UiConstants.COLOR_ERROR),;

    private final Integer value;
    private final String description;
    private final String color;
}
