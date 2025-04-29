package com.xll.frame.system.srv.controller.system;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.feiniaojin.gracefulresponse.api.ExcludeFromGracefulResponse;
import com.xll.frame.starter.extension.crud.core.model.query.PageQuery;
import com.xll.frame.starter.extension.crud.core.model.query.SortQuery;
import com.xll.frame.starter.extension.crud.mp.model.resp.PageResp;
import com.xll.frame.system.infrastructure.service.LogService;
import com.xll.frame.system.infrastructure.model.query.LogQuery;
import com.xll.frame.system.infrastructure.model.resp.log.LogDetailResp;
import com.xll.frame.system.infrastructure.model.resp.log.LogResp;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 功能描述: <br>
 * <p>
 *  <系统日志 API>
 * </p>
 * @author xuliangliang
 * @since 2025/3/23 01:51
 * @version 1.0.0
 */
@Tag(name = "系统日志 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/system/log")
public class LogController {

    private final LogService baseService;

    @Operation(summary = "分页查询列表", description = "分页查询列表")
    @SaCheckPermission("monitor:log:list")
    @GetMapping
    public PageResp<LogResp> page(LogQuery query, @Validated PageQuery pageQuery) {
        return baseService.page(query, pageQuery);
    }

    @Operation(summary = "查询详情", description = "查询详情")
    @Parameter(name = "id", description = "ID", example = "1", in = ParameterIn.PATH)
    @SaCheckPermission("monitor:log:detail")
    @GetMapping("/{id}")
    public LogDetailResp get(@PathVariable Long id) {
        return baseService.get(id);
    }

    @ExcludeFromGracefulResponse
    @Operation(summary = "导出登录日志", description = "导出登录日志")
    @SaCheckPermission("monitor:log:export")
    @GetMapping("/export/login")
    public void exportLoginLog(LogQuery query, SortQuery sortQuery, HttpServletResponse response) {
        baseService.exportLoginLog(query, sortQuery, response);
    }

    @ExcludeFromGracefulResponse
    @Operation(summary = "导出操作日志", description = "导出操作日志")
    @SaCheckPermission("monitor:log:export")
    @GetMapping("/export/operation")
    public void exportOperationLog(LogQuery query, SortQuery sortQuery, HttpServletResponse response) {
        baseService.exportOperationLog(query, sortQuery, response);
    }
}
