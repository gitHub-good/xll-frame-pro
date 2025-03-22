package com.xll.frame.starter.system.infrastructure.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.xll.frame.starter.common.constant.CacheConstants;
import com.xll.frame.starter.data.mp.base.BaseMapper;
import com.xll.frame.starter.system.infrastructure.model.entity.LogDO;
import com.xll.frame.starter.system.infrastructure.model.resp.dashboard.DashboardAccessTrendResp;
import com.xll.frame.starter.system.infrastructure.model.resp.dashboard.DashboardChartCommonResp;
import com.xll.frame.starter.system.infrastructure.model.resp.dashboard.DashboardOverviewCommonResp;
import com.xll.frame.starter.system.infrastructure.model.resp.log.LogResp;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

/**
 * 功能描述: <br>
 * <p>
 *  <系统日志 Mapper>
 * </p>
 * @author xuliangliang
 * @since 2025/3/23 00:28
 * @version 1.0.0
 */
public interface LogMapper extends BaseMapper<LogDO> {

    /**
     * 分页查询列表
     *
     * @param page         分页条件
     * @param queryWrapper 查询条件
     * @return 分页列表信息
     */
    IPage<LogResp> selectLogPage(@Param("page") IPage<LogDO> page,
                                 @Param(Constants.WRAPPER) QueryWrapper<LogDO> queryWrapper);

    /**
     * 查询列表
     *
     * @param queryWrapper 查询条件
     * @return 列表信息
     */
    List<LogResp> selectLogList(@Param(Constants.WRAPPER) QueryWrapper<LogDO> queryWrapper);

    /**
     * 查询总数量
     *
     * @return 总数量
     */
    @Select("SELECT COUNT(*) FROM sys_log")
    Long selectTotalCount();

    /**
     * 查询仪表盘 PV 总览
     *
     * @return 仪表盘 PV 总览
     */
    DashboardOverviewCommonResp selectDashboardOverviewPv();

    /**
     * 查询仪表盘 IP 总览
     *
     * @return 仪表盘 IP 总览
     */
    DashboardOverviewCommonResp selectDashboardOverviewIp();

    /**
     * 查询仪表盘 PV 近 N 月各月份信息
     *
     * @param months 近 N 月月份列表
     * @return 仪表盘 PV 近 N 月各月份信息
     */
    @Cached(key = "#months[0]", name = CacheConstants.DASHBOARD_KEY_PREFIX + "PV:")
    List<DashboardChartCommonResp> selectListDashboardAnalysisPv(@Param("months") List<String> months);

    /**
     * 查询仪表盘 IP 近 N 月各月份信息
     *
     * @param months 近 N 月月份列表
     * @return 仪表盘 IP 近 N 月各月份信息
     */
    @Cached(key = "#months[0]", name = CacheConstants.DASHBOARD_KEY_PREFIX + "IP:")
    List<DashboardChartCommonResp> selectListDashboardAnalysisIp(@Param("months") List<String> months);

    /**
     * 查询仪表盘地域分析信息
     *
     * @return 仪表盘地域分析信息
     */
    List<DashboardChartCommonResp> selectListDashboardAnalysisGeo();

    /**
     * 查询仪表盘访问趋势信息
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return 仪表盘访问趋势信息
     */
    List<DashboardAccessTrendResp> selectListDashboardAccessTrend(@Param("startTime") Date startTime,
                                                                  @Param("endTime") Date endTime);

    /**
     * 查询仪表盘访问时段分析信息
     *
     * @return 仪表盘访问时段分析信息
     */
    List<DashboardChartCommonResp> selectListDashboardAnalysisTimeslot();

    /**
     * 查询仪表盘模块分析信息
     *
     * @param top 显示数量
     * @return 仪表盘模块分析信息
     */
    List<DashboardChartCommonResp> selectListDashboardAnalysisModule(@Param("top") Integer top);

    /**
     * 查询仪表盘终端分析信息
     *
     * @param top 显示数量
     * @return 仪表盘终端分析信息
     */
    List<DashboardChartCommonResp> selectListDashboardAnalysisOs(@Param("top") Integer top);

    /**
     * 查询仪表盘浏览器分析信息
     *
     * @param top 显示数量
     * @return 仪表盘浏览器分析信息
     */
    List<DashboardChartCommonResp> selectListDashboardAnalysisBrowser(@Param("top") Integer top);
}
