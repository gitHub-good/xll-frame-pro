package com.xll.frame.system.domain;

import com.xll.frame.starter.data.mp.service.IService;
import com.xll.frame.starter.extension.crud.core.service.BaseService;
import com.xll.frame.system.infrastructure.model.entity.MenuDO;
import com.xll.frame.system.infrastructure.model.query.MenuQuery;
import com.xll.frame.system.infrastructure.model.req.MenuReq;
import com.xll.frame.system.infrastructure.model.resp.MenuResp;

import java.util.List;
import java.util.Set;

/**
 * 功能描述: <br>
 * <p>
 *  <菜单业务接口>
 * </p>
 * @author xuliangliang
 * @since 2025/3/23 01:01
 * @version 1.0.0
 */
public interface MenuService extends BaseService<MenuResp, MenuResp, MenuQuery, MenuReq>, IService<MenuDO> {

    /**
     * 查询全部菜单
     *
     * @return 菜单列表
     */
    List<MenuResp> listAll();

    /**
     * 根据用户 ID 查询
     *
     * @param userId 用户 ID
     * @return 权限码集合
     */
    Set<String> listPermissionByUserId(Long userId);

    /**
     * 根据角色编码查询
     *
     * @param roleCode 角色编码
     * @return 菜单列表
     */
    List<MenuResp> listByRoleCode(String roleCode);
}
