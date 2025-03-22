package com.xll.frame.system.infrastructure.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * 功能描述: <br>
 * <p>
 *  <角色和菜单实体>
 * </p>
 * @author xuliangliang
 * @since 2025/3/23 00:34
 * @version 1.0.0
 */
@Data
@NoArgsConstructor
@TableName("sys_role_menu")
public class RoleMenuDO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 角色 ID
     */
    private Long roleId;

    /**
     * 菜单 ID
     */
    private Long menuId;

    public RoleMenuDO(Long roleId, Long menuId) {
        this.roleId = roleId;
        this.menuId = menuId;
    }
}
