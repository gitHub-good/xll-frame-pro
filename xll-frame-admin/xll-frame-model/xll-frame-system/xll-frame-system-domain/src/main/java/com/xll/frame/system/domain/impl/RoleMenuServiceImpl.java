package com.xll.frame.system.domain.impl;

import cn.hutool.core.collection.CollUtil;
import com.xll.frame.system.infrastructure.service.RoleMenuService;
import com.xll.frame.system.infrastructure.model.entity.RoleMenuDO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.xll.frame.system.infrastructure.mapper.RoleMenuMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 功能描述: <br>
 * <p>
 *  <角色和菜单业务实现>
 * </p>
 * @author xuliangliang
 * @since 2025/3/23 01:13
 * @version 1.0.0
 */
@Service
@RequiredArgsConstructor
public class RoleMenuServiceImpl implements RoleMenuService {

    private final RoleMenuMapper baseMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean add(List<Long> menuIds, Long roleId) {
        // 检查是否有变更
        List<Long> oldMenuIdList = baseMapper.lambdaQuery()
            .select(RoleMenuDO::getMenuId)
            .eq(RoleMenuDO::getRoleId, roleId)
            .list()
            .stream()
            .map(RoleMenuDO::getMenuId)
            .collect(Collectors.toList());
        if (CollUtil.isEmpty(CollUtil.disjunction(menuIds, oldMenuIdList))) {
            return false;
        }
        // 删除原有关联
        baseMapper.lambdaUpdate().eq(RoleMenuDO::getRoleId, roleId).remove();
        // 保存最新关联
        List<RoleMenuDO> roleMenuList = menuIds.stream().map(menuId -> new RoleMenuDO(roleId, menuId)).toList();
        return baseMapper.insertBatch(roleMenuList);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteByRoleIds(List<Long> roleIds) {
        if (CollUtil.isEmpty(roleIds)) {
            return;
        }
        baseMapper.lambdaUpdate().in(RoleMenuDO::getRoleId, roleIds).remove();
    }

    @Override
    public List<Long> listMenuIdByRoleIds(List<Long> roleIds) {
        if (CollUtil.isEmpty(roleIds)) {
            return new ArrayList<>(0);
        }
        return baseMapper.selectMenuIdByRoleIds(roleIds);
    }
}
