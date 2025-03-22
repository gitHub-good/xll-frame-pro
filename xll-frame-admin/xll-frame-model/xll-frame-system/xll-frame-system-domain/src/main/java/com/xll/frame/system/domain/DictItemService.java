package com.xll.frame.system.domain;

import com.xll.frame.starter.data.mp.service.IService;
import com.xll.frame.starter.extension.crud.core.model.resp.LabelValueResp;
import com.xll.frame.starter.extension.crud.core.service.BaseService;
import com.xll.frame.system.infrastructure.model.entity.DictItemDO;
import com.xll.frame.system.infrastructure.model.query.DictItemQuery;
import com.xll.frame.system.infrastructure.model.req.DictItemReq;
import com.xll.frame.system.infrastructure.model.resp.DictItemResp;

import java.util.List;

/**
 * 功能描述: <br>
 * <p>
 *  <字典项业务接口>
 * </p>
 * @author xuliangliang
 * @since 2025/3/23 00:54
 * @version 1.0.0
 */
public interface DictItemService extends BaseService<DictItemResp, DictItemResp, DictItemQuery, DictItemReq>, IService<DictItemDO> {

    /**
     * 根据字典编码查询
     *
     * @param dictCode 字典编码
     * @return 字典项列表
     */
    List<LabelValueResp> listByDictCode(String dictCode);

    /**
     * 根据字典 ID 列表删除
     *
     * @param dictIds 字典 ID 列表
     */
    void deleteByDictIds(List<Long> dictIds);

    /**
     * 查询枚举字典名称列表
     *
     * @return 枚举字典名称列表
     */
    List<String> listEnumDictNames();
}