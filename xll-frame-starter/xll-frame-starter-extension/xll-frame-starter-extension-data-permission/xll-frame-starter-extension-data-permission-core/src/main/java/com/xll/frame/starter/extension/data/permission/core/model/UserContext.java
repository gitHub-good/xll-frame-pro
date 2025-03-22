package com.xll.frame.starter.extension.data.permission.core.model;

import lombok.Data;

import java.util.Set;

/**
 * 功能描述: <br>
 * <p>
 *  <用户上下文>
 * </p>
 * @author xuliangliang
 * @since 2025/2/23 16:26
 * @version 1.0.0
 */
@Data
public class UserContext {

    /**
     * 用户 ID
     */
    private String userId;

    /**
     * 角色列表
     */
    private Set<RoleContext> roles;

    /**
     * 部门 ID
     */
    private String deptId;
}
