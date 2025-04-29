package com.xll.frame.system.domain.impl;

import cn.crane4j.annotation.ContainerMethod;
import cn.crane4j.annotation.MappingType;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.alicp.jetcache.anno.CacheInvalidate;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.xll.frame.starter.common.constant.CacheConstants;
import com.xll.frame.starter.common.constant.ContainerConstants;
import com.xll.frame.starter.common.constant.SysConstants;
import com.xll.frame.starter.common.context.RoleContext;
import com.xll.frame.starter.common.context.UserContext;
import com.xll.frame.starter.common.context.UserContextHolder;
import com.xll.frame.starter.common.enums.DataScopeEnum;
import com.xll.frame.starter.core.validation.CheckUtils;
import com.xll.frame.starter.extension.crud.mp.service.BaseServiceImpl;
import com.xll.frame.system.infrastructure.model.entity.RoleDO;
import com.xll.frame.system.infrastructure.model.query.RoleQuery;
import com.xll.frame.system.infrastructure.model.req.RoleReq;
import com.xll.frame.system.infrastructure.model.req.RoleUpdatePermissionReq;
import com.xll.frame.system.infrastructure.model.resp.MenuResp;
import com.xll.frame.system.infrastructure.model.resp.role.RoleDetailResp;
import com.xll.frame.system.infrastructure.model.resp.role.RoleResp;
import com.xll.frame.system.infrastructure.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.xll.frame.system.infrastructure.mapper.RoleMapper;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 功能描述: <br>
 * <p>
 *  <角色业务实现>
 * </p>
 * @author xuliangliang
 * @since 2025/3/23 01:15
 * @version 1.0.0
 */
@Service
@RequiredArgsConstructor
public class RoleServiceImpl extends BaseServiceImpl<RoleMapper, RoleDO, RoleResp, RoleDetailResp, RoleQuery, RoleReq> implements RoleService {

    private final MenuService menuService;
    private final RoleMenuService roleMenuService;
    private final RoleDeptService roleDeptService;
    private final UserRoleService userRoleService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long add(RoleReq req) {
        String name = req.getName();
        CheckUtils.throwIf(this.isNameExists(name, null), "新增失败，[{}] 已存在", name);
        String code = req.getCode();
        CheckUtils.throwIf(this.isCodeExists(code, null), "新增失败，[{}] 已存在", code);
        // 新增信息
        Long roleId = super.add(req);
        // 保存角色和部门关联
        roleDeptService.add(req.getDeptIds(), roleId);
        return roleId;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheInvalidate(key = "#req.code == 'admin' ? 'ALL' : #req.code", name = CacheConstants.MENU_KEY_PREFIX)
    public void update(RoleReq req, Long id) {
        String name = req.getName();
        CheckUtils.throwIf(this.isNameExists(name, id), "修改失败，[{}] 已存在", name);
        RoleDO oldRole = super.getById(id);
        CheckUtils.throwIfNotEqual(req.getCode(), oldRole.getCode(), "角色编码不允许修改", oldRole.getName());
        DataScopeEnum oldDataScope = oldRole.getDataScope();
        if (Boolean.TRUE.equals(oldRole.getIsSystem())) {
            CheckUtils.throwIfNotEqual(req.getDataScope(), oldDataScope, "[{}] 是系统内置角色，不允许修改角色数据权限", oldRole.getName());
        }
        // 更新信息
        super.update(req, id);
        if (SysConstants.SUPER_ROLE_CODE.equals(req.getCode())) {
            return;
        }
        // 保存角色和部门关联
        boolean isSaveDeptSuccess = roleDeptService.add(req.getDeptIds(), id);
        // 如果数据权限有变更，则更新在线用户权限信息
        if (isSaveDeptSuccess || ObjectUtil.notEqual(req.getDataScope(), oldDataScope)) {
            this.updateUserContext(id);
        }
    }

    @Override
    public void beforeDelete(List<Long> ids) {
        List<RoleDO> list = baseMapper.lambdaQuery()
            .select(RoleDO::getName, RoleDO::getIsSystem)
            .in(RoleDO::getId, ids)
            .list();
        Optional<RoleDO> isSystemData = list.stream().filter(RoleDO::getIsSystem).findFirst();
        CheckUtils.throwIf(isSystemData::isPresent, "所选角色 [{}] 是系统内置角色，不允许删除", isSystemData.orElseGet(RoleDO::new)
            .getName());
        CheckUtils.throwIf(userRoleService.isRoleIdExists(ids), "所选角色存在用户关联，请解除关联后重试");
        // 删除角色和菜单关联
        roleMenuService.deleteByRoleIds(ids);
        // 删除角色和部门关联
        roleDeptService.deleteByRoleIds(ids);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updatePermission(Long id, RoleUpdatePermissionReq req) {
        super.getById(id);
        // 保存角色和菜单关联
        boolean isSaveMenuSuccess = roleMenuService.add(req.getMenuIds(), id);
        // 如果功能权限有变更，则更新在线用户权限信息
        if (isSaveMenuSuccess) {
            this.updateUserContext(id);
        }
        baseMapper.lambdaUpdate()
            .set(RoleDO::getMenuCheckStrictly, req.getMenuCheckStrictly())
            .eq(RoleDO::getId, id)
            .update();
    }

    @Override
    public void assignToUsers(Long id, List<Long> userIds) {
        super.getById(id);
        // 保存用户和角色关联
        userRoleService.assignRoleToUsers(id, userIds);
        // 更新用户上下文
        this.updateUserContext(id);
    }

    @Override
    public void fill(Object obj) {
        super.fill(obj);
        if (obj instanceof RoleDetailResp detail) {
            Long roleId = detail.getId();
            if (SysConstants.SUPER_ROLE_CODE.equals(detail.getCode())) {
                List<MenuResp> list = menuService.listAll();
                List<Long> menuIds = list.stream().map(MenuResp::getId).toList();
                detail.setMenuIds(menuIds);
            } else {
                detail.setMenuIds(roleMenuService.listMenuIdByRoleIds(CollUtil.newArrayList(roleId)));
            }
        }
    }

    @Override
    public Set<String> listPermissionByUserId(Long userId) {
        Set<String> roleCodeSet = this.listCodeByUserId(userId);
        // 超级管理员赋予全部权限
        if (roleCodeSet.contains(SysConstants.SUPER_ROLE_CODE)) {
            return CollUtil.newHashSet(SysConstants.ALL_PERMISSION);
        }
        return menuService.listPermissionByUserId(userId);
    }

    @Override
    @ContainerMethod(namespace = ContainerConstants.USER_ROLE_NAME_LIST, type = MappingType.ORDER_OF_KEYS)
    public List<String> listNameByIds(List<Long> ids) {
        if (CollUtil.isEmpty(ids)) {
            return Collections.emptyList();
        }
        List<RoleDO> roleList = baseMapper.lambdaQuery().select(RoleDO::getName).in(RoleDO::getId, ids).list();
        return roleList.stream().map(RoleDO::getName).toList();
    }

    @Override
    public Set<String> listCodeByUserId(Long userId) {
        List<Long> roleIdList = userRoleService.listRoleIdByUserId(userId);
        if (CollUtil.isEmpty(roleIdList)) {
            return Collections.emptySet();
        }
        List<RoleDO> roleList = baseMapper.lambdaQuery().select(RoleDO::getCode).in(RoleDO::getId, roleIdList).list();
        return roleList.stream().map(RoleDO::getCode).collect(Collectors.toSet());
    }

    @Override
    public Set<RoleContext> listByUserId(Long userId) {
        List<Long> roleIdList = userRoleService.listRoleIdByUserId(userId);
        if (CollUtil.isEmpty(roleIdList)) {
            return Collections.emptySet();
        }
        List<RoleDO> roleList = baseMapper.lambdaQuery().in(RoleDO::getId, roleIdList).list();
        return new HashSet<>(BeanUtil.copyToList(roleList, RoleContext.class));
    }

    @Override
    public RoleDO getByCode(String code) {
        return baseMapper.lambdaQuery().eq(RoleDO::getCode, code).one();
    }

    @Override
    public List<RoleDO> listByNames(List<String> list) {
        if (CollUtil.isEmpty(list)) {
            return Collections.emptyList();
        }
        return this.list(Wrappers.<RoleDO>lambdaQuery().in(RoleDO::getName, list));
    }

    @Override
    public int countByNames(List<String> roleNames) {
        if (CollUtil.isEmpty(roleNames)) {
            return 0;
        }
        return (int)this.count(Wrappers.<RoleDO>lambdaQuery().in(RoleDO::getName, roleNames));
    }

    /**
     * 名称是否存在
     *
     * @param name 名称
     * @param id   ID
     * @return 是否存在
     */
    private boolean isNameExists(String name, Long id) {
        return baseMapper.lambdaQuery().eq(RoleDO::getName, name).ne(null != id, RoleDO::getId, id).exists();
    }

    /**
     * 编码是否存在
     *
     * @param code 编码
     * @param id   ID
     * @return 是否存在
     */
    private boolean isCodeExists(String code, Long id) {
        return baseMapper.lambdaQuery().eq(RoleDO::getCode, code).ne(null != id, RoleDO::getId, id).exists();
    }

    /**
     * 更新用户上下文
     *
     * @param roleId 角色 ID
     */
    private void updateUserContext(Long roleId) {
        List<Long> userIdList = userRoleService.listUserIdByRoleId(roleId);
        userIdList.parallelStream().forEach(userId -> {
            UserContext userContext = UserContextHolder.getContext(userId);
            if (null != userContext) {
                userContext.setRoles(this.listByUserId(userId));
                userContext.setPermissions(this.listPermissionByUserId(userId));
                UserContextHolder.setContext(userContext);
            }
        });
    }
}
