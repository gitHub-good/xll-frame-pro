package com.xll.frame.system.srv.controller.system;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.xll.frame.system.domain.OptionService;
import com.xll.frame.system.infrastructure.model.query.OptionQuery;
import com.xll.frame.system.infrastructure.model.req.OptionReq;
import com.xll.frame.system.infrastructure.model.req.OptionResetValueReq;
import com.xll.frame.system.infrastructure.model.resp.OptionResp;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 功能描述: <br>
 * <p>
 *  <参数管理 API>
 * </p>
 * @author xuliangliang
 * @since 2025/3/23 01:53
 * @version 1.0.0
 */
@Tag(name = "参数管理 API")
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/system/option")
public class OptionController {

    private final OptionService baseService;

    @Operation(summary = "查询参数列表", description = "查询参数列表")
    @SaCheckPermission("system:config:list")
    @GetMapping
    public List<OptionResp> list(@Validated OptionQuery query) {
        return baseService.list(query);
    }

    @Operation(summary = "修改参数", description = "修改参数")
    @SaCheckPermission("system:config:update")
    @PutMapping
    public void update(@Valid @RequestBody List<OptionReq> options) {
        baseService.update(options);
    }

    @Operation(summary = "重置参数", description = "重置参数")
    @SaCheckPermission("system:config:reset")
    @PatchMapping("/value")
    public void resetValue(@Validated @RequestBody OptionResetValueReq req) {
        baseService.resetValue(req);
    }
}