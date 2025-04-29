package com.xll.frame.system.domain;

import com.xll.frame.starter.data.mp.service.IService;
import com.xll.frame.starter.extension.crud.core.service.BaseService;
import com.xll.frame.system.infrastructure.model.entity.DeptDO;
import com.xll.frame.system.infrastructure.model.query.DeptQuery;
import com.xll.frame.system.infrastructure.model.req.DeptReq;
import com.xll.frame.system.infrastructure.model.resp.DeptResp;

import java.util.List;

/**
 * 功能描述: <br>
 * <p>
 *  <部门业务接口>
 * </p>
 * @author xuliangliang
 * @since 2025/3/23 00:53
 * @version 1.0.0
 */
public interface DeptService extends BaseService<DeptResp, DeptResp, DeptQuery, DeptReq>, IService<DeptDO> {

    /**
     * 查询子部门列表
     *
     * @param id ID
     * @return 子部门列表
     */
    List<DeptDO> listChildren(Long id);

    /**
     * 通过名称查询部门
     *
     * @param list 名称列表
     * @return 部门列表
     */
    List<DeptDO> listByNames(List<String> list);

    /**
     * 通过名称查询部门数量
     *
     * @param deptNames 名称列表
     * @return 部门数量
     */
    int countByNames(List<String> deptNames);
}
