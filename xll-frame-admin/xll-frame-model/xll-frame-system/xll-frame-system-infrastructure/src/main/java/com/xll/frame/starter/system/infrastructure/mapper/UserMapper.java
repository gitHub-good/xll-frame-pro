package com.xll.frame.starter.system.infrastructure.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.xll.frame.starter.common.config.mybatis.DataPermissionMapper;
import com.xll.frame.starter.extension.data.permission.core.annotation.DataPermission;
import com.xll.frame.starter.security.crypto.annotation.FieldEncrypt;
import com.xll.frame.starter.system.infrastructure.model.entity.UserDO;
import com.xll.frame.starter.system.infrastructure.model.resp.user.UserDetailResp;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 功能描述: <br>
 * <p>
 *  <用户 Mapper>
 * </p>
 * @author xuliangliang
 * @since 2025/3/23 00:30
 * @version 1.0.0
 */
public interface UserMapper extends DataPermissionMapper<UserDO> {

    /**
     * 分页查询列表
     *
     * @param page         分页条件
     * @param queryWrapper 查询条件
     * @return 分页列表信息
     */
    @DataPermission(tableAlias = "t1")
    IPage<UserDetailResp> selectUserPage(@Param("page") IPage<UserDO> page,
                                         @Param(Constants.WRAPPER) QueryWrapper<UserDO> queryWrapper);

    /**
     * 查询列表
     *
     * @param queryWrapper 查询条件
     * @return 列表信息
     */
    @DataPermission(tableAlias = "t1")
    List<UserDetailResp> selectUserList(@Param(Constants.WRAPPER) QueryWrapper<UserDO> queryWrapper);

    /**
     * 根据用户名查询
     *
     * @param username 用户名
     * @return 用户信息
     */
    @Select("SELECT * FROM sys_user WHERE username = #{username}")
    UserDO selectByUsername(@Param("username") String username);

    /**
     * 根据手机号查询
     *
     * @param phone 手机号
     * @return 用户信息
     */
    @Select("SELECT * FROM sys_user WHERE phone = #{phone}")
    UserDO selectByPhone(@FieldEncrypt @Param("phone") String phone);

    /**
     * 根据邮箱查询
     *
     * @param email 邮箱
     * @return 用户信息
     */
    @Select("SELECT * FROM sys_user WHERE email = #{email}")
    UserDO selectByEmail(@FieldEncrypt @Param("email") String email);

    /**
     * 根据 ID 查询昵称
     *
     * @param id ID
     * @return 昵称
     */
    @Select("SELECT nickname FROM sys_user WHERE id = #{id}")
    String selectNicknameById(@Param("id") Long id);

    /**
     * 根据邮箱查询数量
     *
     * @param email 邮箱
     * @param id    ID
     * @return 用户数量
     */
    Long selectCountByEmail(@FieldEncrypt @Param("email") String email, @Param("id") Long id);

    /**
     * 根据手机号查询数量
     *
     * @param phone 手机号
     * @param id    ID
     * @return 用户数量
     */
    Long selectCountByPhone(@FieldEncrypt @Param("phone") String phone, @Param("id") Long id);
}
