package com.xll.frame.starter.common.enums;

import com.xll.frame.starter.core.enums.BaseEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 功能描述: <br>
 * <p>
 *  <性别枚举>
 * </p>
 * @author xuliangliang
 * @since 2025/2/23 16:48
 * @version 1.0.0
 */
@Getter
@RequiredArgsConstructor
public enum GenderEnum implements BaseEnum<Integer> {

    /**
     * 未知
     */
    UNKNOWN(0, "未知"),

    /**
     * 男
     */
    MALE(1, "男"),

    /**
     * 女
     */
    FEMALE(2, "女"),;

    private final Integer value;
    private final String description;
}
