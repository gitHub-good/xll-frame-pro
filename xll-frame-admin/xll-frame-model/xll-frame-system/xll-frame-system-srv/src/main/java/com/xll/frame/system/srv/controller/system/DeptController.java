package com.xll.frame.system.srv.controller.system;

import com.xll.frame.starter.common.controller.BaseController;
import com.xll.frame.starter.extension.crud.core.annotation.CrudRequestMapping;
import com.xll.frame.starter.extension.crud.core.enums.Api;
import com.xll.frame.starter.system.domain.system.DeptService;
import com.xll.frame.starter.system.infrastructure.model.query.DeptQuery;
import com.xll.frame.starter.system.infrastructure.model.req.DeptReq;
import com.xll.frame.starter.system.infrastructure.model.resp.DeptResp;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RestController;

/**
 * 功能描述: <br>
 * <p>
 *  <部门管理 API>
 * </p>
 * @author xuliangliang
 * @since 2025/3/23 01:50
 * @version 1.0.0
 */
@Tag(name = "部门管理 API")
@RestController
@CrudRequestMapping(value = "/system/dept", api = {Api.TREE, Api.DETAIL, Api.ADD, Api.UPDATE, Api.DELETE, Api.EXPORT})
public class DeptController extends BaseController<DeptService, DeptResp, DeptResp, DeptQuery, DeptReq> {
}
