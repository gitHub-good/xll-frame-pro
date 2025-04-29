package com.xll.frame.system.domain;

import com.xll.frame.system.infrastructure.model.resp.dashboard.DashboardAccessTrendResp;
import com.xll.frame.system.infrastructure.model.resp.dashboard.DashboardChartCommonResp;
import com.xll.frame.system.infrastructure.model.resp.dashboard.DashboardNoticeResp;
import com.xll.frame.system.infrastructure.model.resp.dashboard.DashboardOverviewCommonResp;

import java.io.IOException;
import java.util.List;

/**
 * 功能描述: <br>
 * <p>
 *  <仪表盘业务接口>
 * </p>
 * @author xuliangliang
 * @since 2025/3/23 00:52
 * @version 1.0.0
 */
public interface DashboardService {

    /**
     * 查询公告列表
     *
     * @return 公告列表
     */
    List<DashboardNoticeResp> listNotice();

    /**
     * 查询 PV 总览
     *
     * @return PV 总览
     */
    DashboardOverviewCommonResp getOverviewPv();

    /**
     * 查询 IP 总览
     *
     * @return IP 总览
     */
    DashboardOverviewCommonResp getOverviewIp();

    /**
     * 查询地域分析信息
     *
     * @return 地域分析信息
     * @throws IOException /
     */
    List<DashboardChartCommonResp> getAnalysisGeo() throws IOException;

    /**
     * 查询访问趋势信息
     *
     * @param days 日期数
     * @return 访问趋势信息
     */
    List<DashboardAccessTrendResp> listAccessTrend(Integer days);

    /**
     * 查询访问时段分析信息
     *
     * @return 访问时段分析信息
     */
    List<DashboardChartCommonResp> getAnalysisTimeslot();

    /**
     * 查询模块分析信息
     *
     * @return 模块分析信息
     */
    List<DashboardChartCommonResp> getAnalysisModule();

    /**
     * 查询终端分析信息
     *
     * @return 终端分析信息
     */
    List<DashboardChartCommonResp> getAnalysisOs();

    /**
     * 查询浏览器分析信息
     *
     * @return 浏览器分析信息
     */
    List<DashboardChartCommonResp> getAnalysisBrowser();
}
