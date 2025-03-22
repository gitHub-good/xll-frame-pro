package com.xll.frame.starter.system.infrastructure.enums;

import com.xll.frame.starter.core.enums.BaseEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 功能描述: <br>
 * <p>
 *  <公告通知范围枚举>
 * </p>
 * @author xuliangliang
 * @since 2025/2/23 18:09
 * @version 1.0.0
 */
@Getter
@RequiredArgsConstructor
public enum NoticeScopeEnum implements BaseEnum<Integer> {

    /**
     * 所有人
     */
    ALL(1, "所有人"),

    /**
     * 指定用户
     */
    USER(2, "指定用户"),;

    private final Integer value;
    private final String description;
}
