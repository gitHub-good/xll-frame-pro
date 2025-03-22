package com.xll.frame.starter.system.domain.system.impl;

import cn.hutool.core.collection.CollUtil;
import com.xll.frame.starter.system.domain.system.RoleDeptService;
import com.xll.frame.starter.system.infrastructure.model.entity.RoleDeptDO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.xll.frame.starter.system.infrastructure.mapper.RoleDeptMapper;

import java.util.List;

/**
 * 功能描述: <br>
 * <p>
 *  <角色和部门业务实现>
 * </p>
 * @author xuliangliang
 * @since 2025/3/23 01:13
 * @version 1.0.0
 */
@Service
@RequiredArgsConstructor
public class RoleDeptServiceImpl implements RoleDeptService {

    private final RoleDeptMapper baseMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean add(List<Long> deptIds, Long roleId) {
        // 检查是否有变更
        List<Long> oldDeptIdList = baseMapper.lambdaQuery()
            .select(RoleDeptDO::getDeptId)
            .eq(RoleDeptDO::getRoleId, roleId)
            .list()
            .stream()
            .map(RoleDeptDO::getDeptId)
            .toList();
        if (CollUtil.isEmpty(CollUtil.disjunction(deptIds, oldDeptIdList))) {
            return false;
        }
        // 删除原有关联
        baseMapper.lambdaUpdate().eq(RoleDeptDO::getRoleId, roleId).remove();
        // 保存最新关联
        List<RoleDeptDO> roleDeptList = deptIds.stream().map(deptId -> new RoleDeptDO(roleId, deptId)).toList();
        return baseMapper.insertBatch(roleDeptList);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteByRoleIds(List<Long> roleIds) {
        if (CollUtil.isEmpty(roleIds)) {
            return;
        }
        baseMapper.lambdaUpdate().in(RoleDeptDO::getRoleId, roleIds).remove();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteByDeptIds(List<Long> deptIds) {
        if (CollUtil.isEmpty(deptIds)) {
            return;
        }
        baseMapper.lambdaUpdate().in(RoleDeptDO::getDeptId, deptIds).remove();
    }

    @Override
    public List<Long> listDeptIdByRoleId(Long roleId) {
        return baseMapper.selectDeptIdByRoleId(roleId);
    }
}
