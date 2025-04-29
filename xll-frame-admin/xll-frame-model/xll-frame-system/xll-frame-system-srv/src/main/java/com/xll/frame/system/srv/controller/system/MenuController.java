package com.xll.frame.system.srv.controller.system;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.xll.frame.starter.common.controller.BaseController;
import com.xll.frame.starter.core.constant.StringConstants;
import com.xll.frame.starter.core.util.URLUtils;
import com.xll.frame.starter.core.validation.ValidationUtils;
import com.xll.frame.starter.extension.crud.core.annotation.CrudApi;
import com.xll.frame.starter.extension.crud.core.annotation.CrudRequestMapping;
import com.xll.frame.starter.extension.crud.core.enums.Api;
import com.xll.frame.system.infrastructure.service.MenuService;
import com.xll.frame.system.infrastructure.model.query.MenuQuery;
import com.xll.frame.system.infrastructure.model.req.MenuReq;
import com.xll.frame.system.infrastructure.model.resp.MenuResp;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Method;

/**
 * 功能描述: <br>
 * <p>
 *  <菜单管理 API>
 * </p>
 * @author xuliangliang
 * @since 2025/3/23 01:52
 * @version 1.0.0
 */
@Tag(name = "菜单管理 API")
@RestController
@CrudRequestMapping(value = "/system/menu", api = {Api.TREE, Api.DETAIL, Api.ADD, Api.UPDATE, Api.DELETE})
public class MenuController extends BaseController<MenuService, MenuResp, MenuResp, MenuQuery, MenuReq> {

    @Override
    public void preHandle(CrudApi crudApi, Object[] args, Method targetMethod, Class<?> targetClass) throws Exception {
        super.preHandle(crudApi, args, targetMethod, targetClass);
        Api api = crudApi.value();
        if (!(Api.ADD.equals(api) || Api.UPDATE.equals(api))) {
            return;
        }
        MenuReq req = (MenuReq)args[0];
        Boolean isExternal = ObjectUtil.defaultIfNull(req.getIsExternal(), false);
        String path = req.getPath();
        ValidationUtils.throwIf(Boolean.TRUE.equals(isExternal) && !URLUtils
            .isHttpUrl(path), "路由地址格式错误，请以 http:// 或 https:// 开头");
        // 非外链菜单参数修正
        if (Boolean.FALSE.equals(isExternal)) {
            ValidationUtils.throwIf(URLUtils.isHttpUrl(path), "路由地址格式错误");
            req.setPath(StrUtil.isBlank(path) ? path : StrUtil.prependIfMissing(path, StringConstants.SLASH));
            req.setName(StrUtil.removePrefix(req.getName(), StringConstants.SLASH));
            req.setComponent(StrUtil.removePrefix(req.getComponent(), StringConstants.SLASH));
        }
    }
}
