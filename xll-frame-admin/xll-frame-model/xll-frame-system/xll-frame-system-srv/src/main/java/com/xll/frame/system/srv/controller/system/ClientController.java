package com.xll.frame.system.srv.controller.system;

import com.xll.frame.starter.common.controller.BaseController;
import com.xll.frame.starter.extension.crud.core.annotation.CrudRequestMapping;
import com.xll.frame.starter.extension.crud.core.enums.Api;
import com.xll.frame.system.infrastructure.service.ClientService;
import com.xll.frame.system.infrastructure.model.query.ClientQuery;
import com.xll.frame.system.infrastructure.model.req.ClientReq;
import com.xll.frame.system.infrastructure.model.resp.ClientResp;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RestController;

/**
 * 功能描述: <br>
 * <p>
 *  <客户端管理 API>
 * </p>
 * @author xuliangliang
 * @since 2025/3/23 01:50
 * @version 1.0.0
 */
@Tag(name = "客户端管理 API")
@RestController
@CrudRequestMapping(value = "/system/client", api = {Api.PAGE, Api.DETAIL, Api.ADD, Api.UPDATE, Api.DELETE})
public class ClientController extends BaseController<ClientService, ClientResp, ClientResp, ClientQuery, ClientReq> {
}