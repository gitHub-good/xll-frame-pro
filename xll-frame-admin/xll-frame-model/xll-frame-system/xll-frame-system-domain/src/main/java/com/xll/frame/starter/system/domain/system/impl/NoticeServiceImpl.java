package com.xll.frame.starter.system.domain.system.impl;

import com.xll.frame.starter.common.context.UserContextHolder;
import com.xll.frame.starter.extension.crud.mp.service.BaseServiceImpl;
import com.xll.frame.starter.system.domain.system.NoticeService;
import com.xll.frame.starter.system.infrastructure.model.entity.NoticeDO;
import com.xll.frame.starter.system.infrastructure.model.query.NoticeQuery;
import com.xll.frame.starter.system.infrastructure.model.req.NoticeReq;
import com.xll.frame.starter.system.infrastructure.model.resp.NoticeDetailResp;
import com.xll.frame.starter.system.infrastructure.model.resp.NoticeResp;
import com.xll.frame.starter.system.infrastructure.model.resp.dashboard.DashboardNoticeResp;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.xll.frame.starter.system.infrastructure.mapper.NoticeMapper;
import java.util.List;

/**
 * 功能描述: <br>
 * <p>
 *  <公告业务实现>
 * </p>
 * @author xuliangliang
 * @since 2025/3/23 01:11
 * @version 1.0.0
 */
@Service
@RequiredArgsConstructor
public class NoticeServiceImpl extends BaseServiceImpl<NoticeMapper, NoticeDO, NoticeResp, NoticeDetailResp, NoticeQuery, NoticeReq> implements NoticeService {

    @Override
    public List<DashboardNoticeResp> listDashboard() {
        Long userId = UserContextHolder.isAdmin() ? null : UserContextHolder.getUserId();
        return baseMapper.selectDashboardList(userId);
    }
}