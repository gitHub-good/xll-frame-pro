package com.xll.frame.system.domain;


import com.xll.frame.starter.extension.crud.core.service.BaseService;
import com.xll.frame.system.infrastructure.model.query.ClientQuery;
import com.xll.frame.system.infrastructure.model.req.ClientReq;
import com.xll.frame.system.infrastructure.model.resp.ClientResp;

/**
 * 功能描述: <br>
 * <p>
 *  <客户端业务接口>
 * </p>
 * @author xuliangliang
 * @since 2025/2/23 18:06
 * @version 1.0.0
 */
public interface ClientService extends BaseService<ClientResp, ClientResp, ClientQuery, ClientReq> {

    /**
     * 根据客户端 ID 查詢
     *
     * @param clientId 客戶端 ID
     * @return 客户端信息
     */
    ClientResp getByClientId(String clientId);
}