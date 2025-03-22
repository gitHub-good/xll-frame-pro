package com.xll.frame.system.infrastructure.mapper;

import com.xll.frame.starter.data.mp.base.BaseMapper;
import com.xll.frame.system.infrastructure.model.entity.RoleMenuDO;

import java.util.List;

/**
 * 功能描述: <br>
 * <p>
 *  <角色和菜单 Mapper>
 * </p>
 * @author xuliangliang
 * @since 2025/3/23 00:30
 * @version 1.0.0
 */
public interface RoleMenuMapper extends BaseMapper<RoleMenuDO> {

    /**
     * 根据角色 ID 列表查询
     *
     * @param roleIds 角色 ID 列表
     * @return 菜单 ID 列表
     */
    List<Long> selectMenuIdByRoleIds(List<Long> roleIds);
}
