package com.xll.frame.starter.common.enums;

import com.xll.frame.starter.core.enums.BaseEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 功能描述: <br>
 * <p>
 *  <数据权限枚举>
 * </p>
 * @author xuliangliang
 * @since 2025/2/23 16:48
 * @version 1.0.0
 */
@Getter
@RequiredArgsConstructor
public enum DataScopeEnum implements BaseEnum<Integer> {

    /**
     * 全部数据权限
     */
    ALL(1, "全部数据权限"),

    /**
     * 本部门及以下数据权限
     */
    DEPT_AND_CHILD(2, "本部门及以下数据权限"),

    /**
     * 本部门数据权限
     */
    DEPT(3, "本部门数据权限"),

    /**
     * 仅本人数据权限
     */
    SELF(4, "仅本人数据权限"),

    /**
     * 自定义数据权限
     */
    CUSTOM(5, "自定义数据权限"),;

    private final Integer value;
    private final String description;
}
