package com.xll.frame.starter.common.config.mybatis;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.xll.frame.starter.data.mp.base.BaseMapper;
import com.xll.frame.starter.extension.data.permission.core.annotation.DataPermission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 功能描述: <br>
 * <p>
 *  <数据权限 Mapper 基类>
 * </p>
 * @author xuliangliang
 * @since 2025/2/23 16:22
 * @version 1.0.0
 */
public interface DataPermissionMapper<T> extends BaseMapper<T> {

    /**
     * 根据 entity 条件，查询全部记录
     *
     * @param queryWrapper 实体对象封装操作类（可以为 null）
     * @return 全部记录
     */
    @Override
    @DataPermission
    List<T> selectList(@Param(Constants.WRAPPER) Wrapper<T> queryWrapper);

    /**
     * 根据 entity 条件，查询全部记录（并翻页）
     *
     * @param page         分页查询条件
     * @param queryWrapper 实体对象封装操作类（可以为 null）
     * @return 全部记录（并翻页）
     */
    @Override
    @DataPermission
    List<T> selectList(IPage<T> page, @Param(Constants.WRAPPER) Wrapper<T> queryWrapper);
}
