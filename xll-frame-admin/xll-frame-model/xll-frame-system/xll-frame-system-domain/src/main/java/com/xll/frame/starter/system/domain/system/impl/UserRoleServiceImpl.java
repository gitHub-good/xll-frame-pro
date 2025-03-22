package com.xll.frame.starter.system.domain.system.impl;

import cn.crane4j.annotation.AutoOperate;
import cn.crane4j.annotation.ContainerMethod;
import cn.crane4j.annotation.MappingType;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xll.frame.starter.common.constant.ContainerConstants;
import com.xll.frame.starter.common.constant.SysConstants;
import com.xll.frame.starter.core.validation.CheckUtils;
import com.xll.frame.starter.data.mp.util.QueryWrapperHelper;
import com.xll.frame.starter.extension.crud.core.model.query.PageQuery;
import com.xll.frame.starter.extension.crud.mp.model.resp.PageResp;
import com.xll.frame.starter.system.domain.system.UserRoleService;
import com.xll.frame.starter.system.infrastructure.model.entity.UserRoleDO;
import com.xll.frame.starter.system.infrastructure.model.query.RoleUserQuery;
import com.xll.frame.starter.system.infrastructure.model.resp.role.RoleUserResp;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.xll.frame.starter.system.infrastructure.mapper.UserRoleMapper;

import java.util.List;

/**
 * 功能描述: <br>
 * <p>
 *  <用户和角色业务实现>
 * </p>
 * @author xuliangliang
 * @since 2025/3/23 01:22
 * @version 1.0.0
 */
@Service
@RequiredArgsConstructor
public class UserRoleServiceImpl implements UserRoleService {

    private final UserRoleMapper baseMapper;

    @Override
    @AutoOperate(type = RoleUserResp.class, on = "list")
    public PageResp<RoleUserResp> pageUser(RoleUserQuery query, PageQuery pageQuery) {
        String description = query.getDescription();
        QueryWrapper<UserRoleDO> queryWrapper = new QueryWrapper<UserRoleDO>().eq("t1.role_id", query.getRoleId())
            .and(StrUtil.isNotBlank(description), q -> q.like("t2.username", description)
                .or()
                .like("t2.nickname", description)
                .or()
                .like("t2.description", description));
        QueryWrapperHelper.sort(queryWrapper, pageQuery.getSort());
        IPage<RoleUserResp> page = baseMapper.selectUserPage(new Page<>(pageQuery.getPage(), pageQuery
            .getSize()), queryWrapper);
        return PageResp.build(page);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean assignRolesToUser(List<Long> roleIds, Long userId) {
        // 检查是否有变更
        List<Long> oldRoleIdList = baseMapper.lambdaQuery()
            .select(UserRoleDO::getRoleId)
            .eq(UserRoleDO::getUserId, userId)
            .list()
            .stream()
            .map(UserRoleDO::getRoleId)
            .toList();
        if (CollUtil.isEmpty(CollUtil.disjunction(roleIds, oldRoleIdList))) {
            return false;
        }
        CheckUtils.throwIf(SysConstants.SUPER_USER_ID.equals(userId) && !roleIds
            .contains(SysConstants.SUPER_ROLE_ID), "不允许变更超管用户角色");
        // 删除原有关联
        baseMapper.lambdaUpdate().eq(UserRoleDO::getUserId, userId).remove();
        // 保存最新关联
        List<UserRoleDO> userRoleList = roleIds.stream().map(roleId -> new UserRoleDO(userId, roleId)).toList();
        return baseMapper.insertBatch(userRoleList);
    }

    @Override
    public boolean assignRoleToUsers(Long roleId, List<Long> userIds) {
        List<UserRoleDO> userRoleList = userIds.stream().map(userId -> new UserRoleDO(userId, roleId)).toList();
        return baseMapper.insertBatch(userRoleList);
    }

    @Override
    public void deleteByIds(List<Long> ids) {
        baseMapper.deleteByIds(ids);
    }

    @Override
    public void deleteByUserIds(List<Long> userIds) {
        if (CollUtil.isEmpty(userIds)) {
            return;
        }
        baseMapper.lambdaUpdate().in(UserRoleDO::getUserId, userIds).remove();
    }

    @Override
    public void saveBatch(List<UserRoleDO> list) {
        baseMapper.insert(list);
    }

    @Override
    @ContainerMethod(namespace = ContainerConstants.USER_ROLE_ID_LIST, type = MappingType.ORDER_OF_KEYS)
    public List<Long> listRoleIdByUserId(Long userId) {
        return baseMapper.lambdaQuery()
            .select(UserRoleDO::getRoleId)
            .eq(UserRoleDO::getUserId, userId)
            .list()
            .stream()
            .map(UserRoleDO::getRoleId)
            .toList();
    }

    @Override
    public List<Long> listUserIdByRoleId(Long roleId) {
        return baseMapper.lambdaQuery()
            .select(UserRoleDO::getUserId)
            .eq(UserRoleDO::getRoleId, roleId)
            .list()
            .stream()
            .map(UserRoleDO::getUserId)
            .toList();
    }

    @Override
    public boolean isRoleIdExists(List<Long> roleIds) {
        if (CollUtil.isEmpty(roleIds)) {
            return false;
        }
        return baseMapper.lambdaQuery().in(UserRoleDO::getRoleId, roleIds).exists();
    }
}
