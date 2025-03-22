package com.xll.frame.starter.common.enums;

import com.xll.frame.starter.common.constant.UiConstants;
import com.xll.frame.starter.core.enums.BaseEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 功能描述: <br>
 * <p>
 *  <成功/失败状态枚举>
 * </p>
 * @author xuliangliang
 * @since 2025/2/23 16:49
 * @version 1.0.0
 */
@Getter
@RequiredArgsConstructor
public enum SuccessFailureStatusEnum implements BaseEnum<Integer> {

    /**
     * 成功
     */
    SUCCESS(1, "成功", UiConstants.COLOR_SUCCESS),

    /**
     * 失败
     */
    FAILURE(2, "失败", UiConstants.COLOR_ERROR),;

    private final Integer value;
    private final String description;
    private final String color;
}
