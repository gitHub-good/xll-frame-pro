package com.xll.frame.system.infrastructure.service;

import com.xll.frame.starter.extension.crud.core.model.query.PageQuery;
import com.xll.frame.starter.extension.crud.core.model.query.SortQuery;
import com.xll.frame.starter.extension.crud.mp.model.resp.PageResp;
import com.xll.frame.system.infrastructure.model.query.LogQuery;
import com.xll.frame.system.infrastructure.model.resp.log.LogDetailResp;
import com.xll.frame.system.infrastructure.model.resp.log.LogResp;
import jakarta.servlet.http.HttpServletResponse;

/**
 * 功能描述: <br>
 * <p>
 *  <系统日志业务接口>
 * </p>
 * @author xuliangliang
 * @since 2025/3/23 00:55
 * @version 1.0.0
 */
public interface LogService {

    /**
     * 分页查询列表
     *
     * @param query     查询条件
     * @param pageQuery 分页查询条件
     * @return 分页列表信息
     */
    PageResp<LogResp> page(LogQuery query, PageQuery pageQuery);

    /**
     * 查询详情
     *
     * @param id ID
     * @return 详情信息
     */
    LogDetailResp get(Long id);

    /**
     * 导出登录日志
     *
     * @param query     查询条件
     * @param sortQuery 排序查询条件
     * @param response  响应对象
     */
    void exportLoginLog(LogQuery query, SortQuery sortQuery, HttpServletResponse response);

    /**
     * 导出操作日志
     *
     * @param query     查询条件
     * @param sortQuery 排序查询条件
     * @param response  响应对象
     */
    void exportOperationLog(LogQuery query, SortQuery sortQuery, HttpServletResponse response);
}
