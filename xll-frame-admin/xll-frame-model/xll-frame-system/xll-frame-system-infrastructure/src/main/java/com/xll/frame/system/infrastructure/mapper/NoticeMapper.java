package com.xll.frame.system.infrastructure.mapper;

import com.xll.frame.starter.data.mp.base.BaseMapper;
import com.xll.frame.system.infrastructure.model.entity.NoticeDO;
import com.xll.frame.system.infrastructure.model.resp.dashboard.DashboardNoticeResp;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 功能描述: <br>
 * <p>
 *  <公告 Mapper>
 * </p>
 * @author xuliangliang
 * @since 2025/3/23 00:29
 * @version 1.0.0
 */
public interface NoticeMapper extends BaseMapper<NoticeDO> {

    /**
     * 查询仪表盘公告列表
     *
     * @param userId 用户 ID
     * @return 仪表盘公告列表
     */
    List<DashboardNoticeResp> selectDashboardList(@Param("userId") Long userId);
}