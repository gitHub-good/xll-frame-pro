package com.xll.frame.starter.extension.data.permission.core.enums;

/**
 * 功能描述: <br>
 * <p>
 *  <数据权限枚举>
 * </p>
 * @author xuliangliang
 * @since 2025/2/23 16:26
 * @version 1.0.0
 */
public enum DataScope {

    /**
     * 全部数据权限
     */
    ALL,

    /**
     * 本部门及以下数据权限
     */
    DEPT_AND_CHILD,

    /**
     * 本部门数据权限
     */
    DEPT,

    /**
     * 仅本人数据权限
     */
    SELF,

    /**
     * 自定义数据权限
     */
    CUSTOM,
}
