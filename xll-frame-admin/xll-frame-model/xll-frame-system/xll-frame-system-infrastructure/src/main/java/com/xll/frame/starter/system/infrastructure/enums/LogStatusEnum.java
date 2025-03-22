package com.xll.frame.starter.system.infrastructure.enums;

import com.xll.frame.starter.core.enums.BaseEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 功能描述: <br>
 * <p>
 *  <操作状态枚举>
 * </p>
 * @author xuliangliang
 * @since 2025/2/23 18:08
 * @version 1.0.0
 */
@Getter
@RequiredArgsConstructor
public enum LogStatusEnum implements BaseEnum<Integer> {

    /**
     * 成功
     */
    SUCCESS(1, "成功"),

    /**
     * 失败
     */
    FAILURE(2, "失败"),;

    private final Integer value;
    private final String description;
}
