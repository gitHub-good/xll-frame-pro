package com.xll.frame.starter.system.domain.system.impl;

import cn.crane4j.annotation.AutoOperate;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xll.frame.starter.common.enums.DisEnableStatusEnum;
import com.xll.frame.starter.core.validation.CheckUtils;
import com.xll.frame.starter.data.mp.util.QueryWrapperHelper;
import com.xll.frame.starter.extension.crud.core.model.query.PageQuery;
import com.xll.frame.starter.extension.crud.core.model.query.SortQuery;
import com.xll.frame.starter.extension.crud.mp.model.resp.PageResp;
import com.xll.frame.starter.file.excel.util.ExcelUtils;
import com.xll.frame.starter.system.domain.system.LogService;
import com.xll.frame.starter.system.infrastructure.model.entity.LogDO;
import com.xll.frame.starter.system.infrastructure.model.query.LogQuery;
import com.xll.frame.starter.system.infrastructure.model.resp.log.LogDetailResp;
import com.xll.frame.starter.system.infrastructure.model.resp.log.LogResp;
import com.xll.frame.starter.system.infrastructure.model.resp.log.LoginLogExportResp;
import com.xll.frame.starter.system.infrastructure.model.resp.log.OperationLogExportResp;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.xll.frame.starter.system.infrastructure.mapper.LogMapper;


import java.util.Date;
import java.util.List;

/**
 * 功能描述: <br>
 * <p>
 *  <系统日志业务实现>
 * </p>
 * @author xuliangliang
 * @since 2025/3/23 00:55
 * @version 1.0.0
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class LogServiceImpl implements LogService {

    private final LogMapper baseMapper;

    @Override
    public PageResp<LogResp> page(LogQuery query, PageQuery pageQuery) {
        QueryWrapper<LogDO> queryWrapper = this.buildQueryWrapper(query);
        QueryWrapperHelper.sort(queryWrapper, pageQuery.getSort());
        IPage<LogResp> page = baseMapper.selectLogPage(new Page<>(pageQuery.getPage(), pageQuery
            .getSize()), queryWrapper);
        return PageResp.build(page);
    }

    @Override
    @AutoOperate(type = LogDetailResp.class)
    public LogDetailResp get(Long id) {
        LogDO logDO = baseMapper.selectById(id);
        CheckUtils.throwIfNotExists(logDO, "LogDO", "ID", id);
        return BeanUtil.copyProperties(logDO, LogDetailResp.class);
    }

    @Override
    public void exportLoginLog(LogQuery query, SortQuery sortQuery, HttpServletResponse response) {
        List<LoginLogExportResp> list = BeanUtil.copyToList(this.list(query, sortQuery), LoginLogExportResp.class);
        ExcelUtils.export(list, "导出登录日志数据", LoginLogExportResp.class, response);
    }

    @Override
    public void exportOperationLog(LogQuery query, SortQuery sortQuery, HttpServletResponse response) {
        List<OperationLogExportResp> list = BeanUtil.copyToList(this
            .list(query, sortQuery), OperationLogExportResp.class);
        ExcelUtils.export(list, "导出操作日志数据", OperationLogExportResp.class, response);
    }

    /**
     * 查询列表
     *
     * @param query     查询条件
     * @param sortQuery 排序查询条件
     * @return 列表信息
     */
    private List<LogResp> list(LogQuery query, SortQuery sortQuery) {
        QueryWrapper<LogDO> queryWrapper = this.buildQueryWrapper(query);
        QueryWrapperHelper.sort(queryWrapper, sortQuery.getSort());
        return baseMapper.selectLogList(queryWrapper);
    }

    /**
     * 构建 QueryWrapper
     *
     * @param query 查询条件
     * @return QueryWrapper
     */
    private QueryWrapper<LogDO> buildQueryWrapper(LogQuery query) {
        String description = query.getDescription();
        String module = query.getModule();
        String ip = query.getIp();
        String createUserString = query.getCreateUserString();
        DisEnableStatusEnum status = query.getStatus();
        List<Date> createTimeList = query.getCreateTime();
        return new QueryWrapper<LogDO>().and(StrUtil.isNotBlank(description), q -> q.like("t1.description", description)
            .or()
            .like("t1.module", description))
            .eq(StrUtil.isNotBlank(module), "t1.module", module)
            .and(StrUtil.isNotBlank(ip), q -> q.like("t1.ip", ip).or().like("t1.address", ip))
            .and(StrUtil.isNotBlank(createUserString), q -> q.like("t2.username", createUserString)
                .or()
                .like("t2.nickname", createUserString))
            .eq(null != status, "t1.status", status)
            .between(CollUtil.isNotEmpty(createTimeList), "t1.create_time", CollUtil.getFirst(createTimeList), CollUtil
                .getLast(createTimeList));
    }
}
