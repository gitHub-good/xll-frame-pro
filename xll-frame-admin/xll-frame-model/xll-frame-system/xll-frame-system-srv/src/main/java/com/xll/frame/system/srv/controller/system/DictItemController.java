package com.xll.frame.system.srv.controller.system;

import com.xll.frame.starter.common.controller.BaseController;
import com.xll.frame.starter.extension.crud.core.annotation.CrudRequestMapping;
import com.xll.frame.starter.extension.crud.core.enums.Api;
import com.xll.frame.starter.log.core.annotation.Log;
import com.xll.frame.starter.system.domain.system.DictItemService;
import com.xll.frame.starter.system.infrastructure.model.query.DictItemQuery;
import com.xll.frame.starter.system.infrastructure.model.req.DictItemReq;
import com.xll.frame.starter.system.infrastructure.model.resp.DictItemResp;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RestController;

/**
 * 功能描述: <br>
 * <p>
 *  <字典项管理 API>
 * </p>
 * @author xuliangliang
 * @since 2025/3/23 01:51
 * @version 1.0.0
 */
@Log(module = "字典管理")
@Tag(name = "字典项管理 API")
@RestController
@CrudRequestMapping(value = "/system/dict/item", api = {Api.PAGE, Api.DETAIL, Api.ADD, Api.UPDATE, Api.DELETE})
public class DictItemController extends BaseController<DictItemService, DictItemResp, DictItemResp, DictItemQuery, DictItemReq> {
}