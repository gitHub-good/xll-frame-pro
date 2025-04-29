package com.xll.frame.system.domain;

import com.xll.frame.starter.common.context.RoleContext;
import com.xll.frame.starter.data.mp.service.IService;
import com.xll.frame.starter.extension.crud.core.service.BaseService;
import com.xll.frame.system.infrastructure.model.entity.RoleDO;
import com.xll.frame.system.infrastructure.model.query.RoleQuery;
import com.xll.frame.system.infrastructure.model.req.RoleReq;
import com.xll.frame.system.infrastructure.model.req.RoleUpdatePermissionReq;
import com.xll.frame.system.infrastructure.model.resp.role.RoleDetailResp;
import com.xll.frame.system.infrastructure.model.resp.role.RoleResp;

import java.util.List;
import java.util.Set;

/**
 * 功能描述: <br>
 * <p>
 *  <角色业务接口>
 * </p>
 * @author xuliangliang
 * @since 2025/3/23 01:13
 * @version 1.0.0
 */
public interface RoleService extends BaseService<RoleResp, RoleDetailResp, RoleQuery, RoleReq>, IService<RoleDO> {

    /**
     * 修改角色权限
     *
     * @param id  角色 ID
     * @param req 参数
     */
    void updatePermission(Long id, RoleUpdatePermissionReq req);

    /**
     * 分配角色给用户
     *
     * @param id      角色 ID
     * @param userIds 用户 ID 列表
     */
    void assignToUsers(Long id, List<Long> userIds);

    /**
     * 根据用户 ID 查询权限码
     *
     * @param userId 用户 ID
     * @return 权限码集合
     */
    Set<String> listPermissionByUserId(Long userId);

    /**
     * 根据 ID 列表查询
     *
     * @param ids ID 列表
     * @return 名称列表
     */
    List<String> listNameByIds(List<Long> ids);

    /**
     * 根据用户 ID 查询角色编码
     *
     * @param userId 用户 ID
     * @return 角色编码集合
     */
    Set<String> listCodeByUserId(Long userId);

    /**
     * 根据用户 ID 查询角色
     *
     * @param userId 用户 ID
     * @return 角色集合
     */
    Set<RoleContext> listByUserId(Long userId);

    /**
     * 根据角色编码查询
     *
     * @param code 角色编码
     * @return 角色信息
     */
    RoleDO getByCode(String code);

    /**
     * 根据角色名称查询
     *
     * @param list 名称列表
     * @return 角色列表
     */
    List<RoleDO> listByNames(List<String> list);

    /**
     * 根据角色名称查询数量
     *
     * @param roleNames 名称列表
     * @return 角色数量
     */
    int countByNames(List<String> roleNames);
}
