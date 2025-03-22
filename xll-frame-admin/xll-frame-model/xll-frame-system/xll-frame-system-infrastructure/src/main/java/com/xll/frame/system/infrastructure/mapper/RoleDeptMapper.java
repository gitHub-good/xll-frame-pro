package com.xll.frame.system.infrastructure.mapper;

import com.xll.frame.starter.data.mp.base.BaseMapper;
import com.xll.frame.system.infrastructure.model.entity.RoleDeptDO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 功能描述: <br>
 * <p>
 *  <角色和部门 Mapper>
 * </p>
 * @author xuliangliang
 * @since 2025/3/23 00:29
 * @version 1.0.0
 */
public interface RoleDeptMapper extends BaseMapper<RoleDeptDO> {

    /**
     * 根据角色 ID 查询
     *
     * @param roleId 角色 ID
     * @return 部门 ID 列表
     */
    @Select("SELECT dept_id FROM sys_role_dept WHERE role_id = #{roleId}")
    List<Long> selectDeptIdByRoleId(@Param("roleId") Long roleId);
}
