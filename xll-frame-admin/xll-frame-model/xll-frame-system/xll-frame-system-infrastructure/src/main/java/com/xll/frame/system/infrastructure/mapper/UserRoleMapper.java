package com.xll.frame.system.infrastructure.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.xll.frame.starter.data.mp.base.BaseMapper;
import com.xll.frame.system.infrastructure.model.entity.UserRoleDO;
import com.xll.frame.system.infrastructure.model.resp.role.RoleUserResp;
import org.apache.ibatis.annotations.Param;

/**
 * 功能描述: <br>
 * <p>
 *  <用户和角色 Mapper>
 * </p>
 * @author xuliangliang
 * @since 2025/3/23 00:31
 * @version 1.0.0
 */
public interface UserRoleMapper extends BaseMapper<UserRoleDO> {

    /**
     * 分页查询列表
     *
     * @param page         分页条件
     * @param queryWrapper 查询条件
     * @return 分页列表信息
     */
    IPage<RoleUserResp> selectUserPage(@Param("page") IPage<UserRoleDO> page,
                                       @Param(Constants.WRAPPER) QueryWrapper<UserRoleDO> queryWrapper);

}
