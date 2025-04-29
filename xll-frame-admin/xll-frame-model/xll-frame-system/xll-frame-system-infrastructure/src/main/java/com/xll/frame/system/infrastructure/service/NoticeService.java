package com.xll.frame.system.domain;

import com.xll.frame.starter.data.mp.service.IService;
import com.xll.frame.starter.extension.crud.core.service.BaseService;
import com.xll.frame.system.infrastructure.model.entity.NoticeDO;
import com.xll.frame.system.infrastructure.model.query.NoticeQuery;
import com.xll.frame.system.infrastructure.model.req.NoticeReq;
import com.xll.frame.system.infrastructure.model.resp.NoticeDetailResp;
import com.xll.frame.system.infrastructure.model.resp.NoticeResp;
import com.xll.frame.system.infrastructure.model.resp.dashboard.DashboardNoticeResp;

import java.util.List;

/**
 * 功能描述: <br>
 * <p>
 *  <公告业务接口>
 * </p>
 * @author xuliangliang
 * @since 2025/3/23 01:11
 * @version 1.0.0
 */
public interface NoticeService extends BaseService<NoticeResp, NoticeDetailResp, NoticeQuery, NoticeReq>, IService<NoticeDO> {

    /**
     * 查询仪表盘公告列表
     *
     * @return 仪表盘公告列表
     */
    List<DashboardNoticeResp> listDashboard();
}