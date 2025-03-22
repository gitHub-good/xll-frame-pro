package com.xll.frame.system.domain;

import com.xll.frame.starter.data.mp.service.IService;
import com.xll.frame.starter.extension.crud.core.model.resp.LabelValueResp;
import com.xll.frame.starter.extension.crud.core.service.BaseService;
import com.xll.frame.system.infrastructure.model.entity.DictDO;
import com.xll.frame.system.infrastructure.model.query.DictQuery;
import com.xll.frame.system.infrastructure.model.req.DictReq;
import com.xll.frame.system.infrastructure.model.resp.DictResp;

import java.util.List;

/**
 * 功能描述: <br>
 * <p>
 *  <字典业务接口>
 * </p>
 * @author xuliangliang
 * @since 2025/3/23 00:55
 * @version 1.0.0
 */
public interface DictService extends BaseService<DictResp, DictResp, DictQuery, DictReq>, IService<DictDO> {

    /**
     * 查询枚举字典
     *
     * @return 枚举字典列表
     */
    List<LabelValueResp> listEnumDict();
}