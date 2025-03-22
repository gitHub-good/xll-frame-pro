package com.xll.frame.system.srv.controller.system;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.xll.frame.starter.common.controller.BaseController;
import com.xll.frame.starter.extension.crud.core.annotation.CrudRequestMapping;
import com.xll.frame.starter.extension.crud.core.enums.Api;
import com.xll.frame.starter.extension.crud.core.model.query.PageQuery;
import com.xll.frame.starter.extension.crud.mp.model.resp.PageResp;
import com.xll.frame.system.domain.RoleService;
import com.xll.frame.system.domain.UserRoleService;
import com.xll.frame.system.infrastructure.model.query.RoleQuery;
import com.xll.frame.system.infrastructure.model.query.RoleUserQuery;
import com.xll.frame.system.infrastructure.model.req.RoleReq;
import com.xll.frame.system.infrastructure.model.req.RoleUpdatePermissionReq;
import com.xll.frame.system.infrastructure.model.resp.role.RoleDetailResp;
import com.xll.frame.system.infrastructure.model.resp.role.RoleResp;
import com.xll.frame.system.infrastructure.model.resp.role.RoleUserResp;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotEmpty;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 功能描述: <br>
 * <p>
 *  < 角色管理 API>
 * </p>
 * @author xuliangliang
 * @since 2025/3/23 01:53
 * @version 1.0.0
 */
@Tag(name = "角色管理 API")
@Validated
@RestController
@RequiredArgsConstructor
@CrudRequestMapping(value = "/system/role", api = {Api.LIST, Api.DETAIL, Api.ADD, Api.UPDATE, Api.DELETE})
public class RoleController extends BaseController<RoleService, RoleResp, RoleDetailResp, RoleQuery, RoleReq> {

    private final UserRoleService userRoleService;

    @Operation(summary = "修改权限", description = "修改角色的功能权限")
    @SaCheckPermission("system:role:updatePermission")
    @PutMapping("/{id}/permission")
    public void updatePermission(@PathVariable("id") Long id, @Validated @RequestBody RoleUpdatePermissionReq req) {
        baseService.updatePermission(id, req);
    }

    @Operation(summary = "分页查询关联用户", description = "分页查询角色关联的用户列表")
    @Parameter(name = "id", description = "ID", example = "1", in = ParameterIn.PATH)
    @SaCheckPermission("system:role:list")
    @GetMapping("/{id}/user")
    public PageResp<RoleUserResp> pageUser(@PathVariable("id") Long id,
                                           @Validated RoleUserQuery query,
                                           @Validated PageQuery pageQuery) {
        query.setRoleId(id);
        return userRoleService.pageUser(query, pageQuery);
    }

    @Operation(summary = "分配用户", description = "批量分配角色给用户")
    @SaCheckPermission("system:role:assign")
    @PostMapping("/{id}/user")
    public void assignToUsers(@PathVariable("id") Long id,
                              @Validated @NotEmpty(message = "用户ID列表不能为空") @RequestBody List<Long> userIds) {
        baseService.assignToUsers(id, userIds);
    }

    @Operation(summary = "取消分配用户", description = "批量取消分配角色给用户")
    @SaCheckPermission("system:role:unassign")
    @DeleteMapping("/user")
    public void unassignFromUsers(@Validated @NotEmpty(message = "用户列表不能为空") @RequestBody List<Long> userRoleIds) {
        userRoleService.deleteByIds(userRoleIds);
    }

    @Operation(summary = "查询关联用户ID", description = "查询角色关联的用户ID列表")
    @Parameter(name = "id", description = "ID", example = "1", in = ParameterIn.PATH)
    @SaCheckPermission("system:role:list")
    @GetMapping("/{id}/user/id")
    public List<Long> listUserId(@PathVariable("id") Long id) {
        return userRoleService.listUserIdByRoleId(id);
    }
}
