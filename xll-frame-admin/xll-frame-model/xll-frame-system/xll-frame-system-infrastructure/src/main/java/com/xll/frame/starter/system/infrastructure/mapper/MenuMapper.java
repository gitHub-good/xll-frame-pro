package com.xll.frame.starter.system.infrastructure.mapper;

import com.xll.frame.starter.data.mp.base.BaseMapper;
import com.xll.frame.starter.system.infrastructure.model.entity.MenuDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * 功能描述: <br>
 * <p>
 *  <菜单 Mapper>
 * </p>
 * @author xuliangliang
 * @since 2025/3/23 00:28
 * @version 1.0.0
 */
public interface MenuMapper extends BaseMapper<MenuDO> {

    /**
     * 根据用户 ID 查询权限码
     *
     * @param userId 用户 ID
     * @return 权限码集合
     */
    Set<String> selectPermissionByUserId(@Param("userId") Long userId);

    /**
     * 根据角色编码查询
     *
     * @param roleCode 角色编码
     * @return 菜单列表
     */
    List<MenuDO> selectListByRoleCode(@Param("roleCode") String roleCode);
}
