package com.xll.frame.starter.system.infrastructure.enums;

import com.xll.frame.starter.core.enums.BaseEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
/**
 * 功能描述: <br>
 * <p>
 *  <菜单类型枚举>
 * </p>
 * @author xuliangliang
 * @since 2025/2/23 18:08
 * @version 1.0.0
 */
@Getter
@RequiredArgsConstructor
public enum MenuTypeEnum implements BaseEnum<Integer> {

    /**
     * 目录
     */
    DIR(1, "目录"),

    /**
     * 菜单
     */
    MENU(2, "菜单"),

    /**
     * 按钮
     */
    BUTTON(3, "按钮"),;

    private final Integer value;
    private final String description;
}
