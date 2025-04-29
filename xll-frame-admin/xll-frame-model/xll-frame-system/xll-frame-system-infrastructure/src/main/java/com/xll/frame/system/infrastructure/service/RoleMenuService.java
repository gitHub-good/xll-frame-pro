package com.xll.frame.system.infrastructure.service;

import java.util.List;

/**
 * 功能描述: <br>
 * <p>
 *  <角色和菜单业务接口>
 * </p>
 * @author xuliangliang
 * @since 2025/3/23 01:13
 * @version 1.0.0
 */
public interface RoleMenuService {

    /**
     * 新增
     *
     * @param menuIds 菜单 ID 列表
     * @param roleId  角色 ID
     * @return 是否新增成功（true：成功；false：无变更/失败）
     */
    boolean add(List<Long> menuIds, Long roleId);

    /**
     * 根据角色 ID 删除
     *
     * @param roleIds 角色 ID 列表
     */
    void deleteByRoleIds(List<Long> roleIds);

    /**
     * 根据角色 ID 查询
     *
     * @param roleIds 角色 ID 列表
     * @return 菜单 ID 列表
     */
    List<Long> listMenuIdByRoleIds(List<Long> roleIds);
}