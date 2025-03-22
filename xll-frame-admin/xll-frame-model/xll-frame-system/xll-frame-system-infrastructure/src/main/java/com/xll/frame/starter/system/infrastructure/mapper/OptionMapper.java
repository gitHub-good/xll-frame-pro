package com.xll.frame.starter.system.infrastructure.mapper;

import com.xll.frame.starter.data.mp.base.BaseMapper;
import com.xll.frame.starter.system.infrastructure.model.entity.OptionDO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 功能描述: <br>
 * <p>
 *  <参数 Mapper>
 * </p>
 * @author xuliangliang
 * @since 2025/3/23 00:29
 * @version 1.0.0
 */
public interface OptionMapper extends BaseMapper<OptionDO> {

    /**
     * 根据类别查询
     *
     * @param category 类别
     * @return 列表
     */
    @Select("SELECT code, value, default_value FROM sys_option WHERE category = #{category}")
    List<OptionDO> selectByCategory(@Param("category") String category);
}