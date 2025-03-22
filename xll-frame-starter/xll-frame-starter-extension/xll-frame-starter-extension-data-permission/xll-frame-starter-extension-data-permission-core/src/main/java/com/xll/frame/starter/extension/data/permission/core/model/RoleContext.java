package com.xll.frame.starter.extension.data.permission.core.model;

import com.xll.frame.starter.extension.data.permission.core.enums.DataScope;
import lombok.Data;

/**
 * 功能描述: <br>
 * <p>
 *  <角色上下文>
 * </p>
 * @author xuliangliang
 * @since 2025/2/23 16:26
 * @version 1.0.0
 */
@Data
public class RoleContext {

    /**
     * 角色 ID
     */
    private String roleId;

    /**
     * 数据权限
     */
    private DataScope dataScope;

    public RoleContext() {
    }

    public RoleContext(String roleId, DataScope dataScope) {
        this.roleId = roleId;
        this.dataScope = dataScope;
    }
}
