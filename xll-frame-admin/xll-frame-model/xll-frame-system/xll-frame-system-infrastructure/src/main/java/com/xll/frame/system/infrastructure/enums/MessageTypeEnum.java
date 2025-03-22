package com.xll.frame.system.infrastructure.enums;

import com.xll.frame.starter.common.constant.UiConstants;
import com.xll.frame.starter.core.enums.BaseEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 功能描述: <br>
 * <p>
 *  <消息类型枚举>
 * </p>
 * @author xuliangliang
 * @since 2025/2/23 18:09
 * @version 1.0.0
 */
@Getter
@RequiredArgsConstructor
public enum MessageTypeEnum implements BaseEnum<Integer> {

    /**
     * 安全消息
     */
    SECURITY(1, "安全消息", UiConstants.COLOR_PRIMARY),;

    private final Integer value;
    private final String description;
    private final String color;
}
