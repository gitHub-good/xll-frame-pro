package com.xll.frame.system.infrastructure.mapper;

import com.xll.frame.starter.data.mp.base.BaseMapper;
import com.xll.frame.starter.extension.crud.core.model.resp.LabelValueResp;
import com.xll.frame.system.infrastructure.model.entity.DictItemDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 功能描述: <br>
 * <p>
 *  <字典项 Mapper>
 * </p>
 * @author xuliangliang
 * @since 2025/3/23 00:27
 * @version 1.0.0
 */
public interface DictItemMapper extends BaseMapper<DictItemDO> {

    /**
     * 根据字典编码查询
     *
     * @param dictCode 字典编码
     * @return 字典项列表
     */
    List<LabelValueResp> listByDictCode(@Param("dictCode") String dictCode);
}