package com.xll.frame.system.srv.controller.system;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.hutool.core.util.ReUtil;
import com.xll.frame.starter.common.constant.RegexConstants;
import com.xll.frame.starter.common.controller.BaseController;
import com.xll.frame.starter.common.util.SecureUtils;
import com.xll.frame.starter.core.util.ExceptionUtils;
import com.xll.frame.starter.core.validation.ValidationUtils;
import com.xll.frame.starter.extension.crud.core.annotation.CrudRequestMapping;
import com.xll.frame.starter.extension.crud.core.enums.Api;
import com.xll.frame.starter.extension.crud.core.model.resp.BaseIdResp;
import com.xll.frame.starter.extension.crud.core.validation.CrudValidationGroup;
import com.xll.frame.system.infrastructure.service.UserService;
import com.xll.frame.system.infrastructure.model.query.UserQuery;
import com.xll.frame.system.infrastructure.model.req.user.UserImportReq;
import com.xll.frame.system.infrastructure.model.req.user.UserPasswordResetReq;
import com.xll.frame.system.infrastructure.model.req.user.UserReq;
import com.xll.frame.system.infrastructure.model.req.user.UserRoleUpdateReq;
import com.xll.frame.system.infrastructure.model.resp.user.UserDetailResp;
import com.xll.frame.system.infrastructure.model.resp.user.UserImportParseResp;
import com.xll.frame.system.infrastructure.model.resp.user.UserImportResp;
import com.xll.frame.system.infrastructure.model.resp.user.UserResp;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * 功能描述: <br>
 * <p>
 *  <用户管理 API>
 * </p>
 * @author xuliangliang
 * @since 2025/3/23 01:54
 * @version 1.0.0
 */
@Tag(name = "用户管理 API")
@Validated
@RestController
@RequiredArgsConstructor
@CrudRequestMapping(value = "/system/user", api = {Api.PAGE, Api.LIST, Api.DETAIL, Api.ADD, Api.UPDATE, Api.DELETE,
    Api.EXPORT})
public class UserController extends BaseController<UserService, UserResp, UserDetailResp, UserQuery, UserReq> {

    @Override
    @Operation(summary = "新增数据", description = "新增数据")
    public BaseIdResp<Long> add(@Validated(CrudValidationGroup.Add.class) @RequestBody UserReq req) {
        String rawPassword = ExceptionUtils.exToNull(() -> SecureUtils.decryptByRsaPrivateKey(req.getPassword()));
        ValidationUtils.throwIfNull(rawPassword, "密码解密失败");
        ValidationUtils.throwIf(!ReUtil
            .isMatch(RegexConstants.PASSWORD, rawPassword), "密码长度为 8-32 个字符，支持大小写字母、数字、特殊字符，至少包含字母和数字");
        req.setPassword(rawPassword);
        return super.add(req);
    }

    @Operation(summary = "下载导入模板", description = "下载导入模板")
    @SaCheckPermission("system:user:import")
    @GetMapping(value = "/import/template", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public void downloadImportTemplate(HttpServletResponse response) throws IOException {
        baseService.downloadImportTemplate(response);
    }

    @Operation(summary = "解析导入数据", description = "解析导入数据")
    @SaCheckPermission("system:user:import")
    @PostMapping("/import/parse")
    public UserImportParseResp parseImport(@NotNull(message = "文件不能为空") MultipartFile file) {
        ValidationUtils.throwIf(file::isEmpty, "文件不能为空");
        return baseService.parseImport(file);
    }

    @Operation(summary = "导入数据", description = "导入数据")
    @SaCheckPermission("system:user:import")
    @PostMapping(value = "/import")
    public UserImportResp importUser(@Validated @RequestBody UserImportReq req) {
        return baseService.importUser(req);
    }

    @Operation(summary = "重置密码", description = "重置用户登录密码")
    @Parameter(name = "id", description = "ID", example = "1", in = ParameterIn.PATH)
    @SaCheckPermission("system:user:resetPwd")
    @PatchMapping("/{id}/password")
    public void resetPassword(@Validated @RequestBody UserPasswordResetReq req, @PathVariable Long id) {
        String rawNewPassword = ExceptionUtils.exToNull(() -> SecureUtils.decryptByRsaPrivateKey(req.getNewPassword()));
        ValidationUtils.throwIfNull(rawNewPassword, "新密码解密失败");
        ValidationUtils.throwIf(!ReUtil
            .isMatch(RegexConstants.PASSWORD, rawNewPassword), "密码长度为 8-32 个字符，支持大小写字母、数字、特殊字符，至少包含字母和数字");
        req.setNewPassword(rawNewPassword);
        baseService.resetPassword(req, id);
    }

    @Operation(summary = "分配角色", description = "为用户新增或移除角色")
    @Parameter(name = "id", description = "ID", example = "1", in = ParameterIn.PATH)
    @SaCheckPermission("system:user:updateRole")
    @PatchMapping("/{id}/role")
    public void updateRole(@Validated @RequestBody UserRoleUpdateReq updateReq, @PathVariable Long id) {
        baseService.updateRole(updateReq, id);
    }
}
