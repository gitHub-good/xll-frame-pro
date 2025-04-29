package com.xll.frame.system.srv.controller.system;

import com.xll.frame.starter.common.controller.BaseController;
import com.xll.frame.starter.extension.crud.core.annotation.CrudRequestMapping;
import com.xll.frame.starter.extension.crud.core.enums.Api;
import com.xll.frame.system.infrastructure.service.DictService;
import com.xll.frame.system.infrastructure.model.query.DictQuery;
import com.xll.frame.system.infrastructure.model.req.DictReq;
import com.xll.frame.system.infrastructure.model.resp.DictResp;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RestController;

/**
 * 功能描述: <br>
 * <p>
 *  <字典管理 API>
 * </p>
 * @author xuliangliang
 * @since 2025/3/23 01:51
 * @version 1.0.0
 */
@Tag(name = "字典管理 API")
@RestController
@CrudRequestMapping(value = "/system/dict", api = {Api.LIST, Api.DETAIL, Api.ADD, Api.UPDATE, Api.DELETE})
public class DictController extends BaseController<DictService, DictResp, DictResp, DictQuery, DictReq> {
}