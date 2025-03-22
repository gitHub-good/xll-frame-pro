package com.xll.frame.starter.system.domain.system.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.xll.frame.starter.core.constant.StringConstants;
import com.xll.frame.starter.core.validation.CheckUtils;
import com.xll.frame.starter.extension.crud.mp.service.BaseServiceImpl;
import com.xll.frame.starter.system.domain.auth.OnlineUserService;
import com.xll.frame.starter.system.domain.system.ClientService;
import com.xll.frame.starter.system.infrastructure.auth.model.query.OnlineUserQuery;
import com.xll.frame.starter.system.infrastructure.model.entity.ClientDO;
import com.xll.frame.starter.system.infrastructure.model.query.ClientQuery;
import com.xll.frame.starter.system.infrastructure.model.req.ClientReq;
import com.xll.frame.starter.system.infrastructure.model.resp.ClientResp;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.xll.frame.starter.system.infrastructure.mapper.ClientMapper;

import java.util.List;

/**
 * 功能描述: <br>
 * <p>
 *  <客户端业务实现>
 * </p>
 * @author xuliangliang
 * @since 2025/3/23 00:52
 * @version 1.0.0
 */
@Service
@RequiredArgsConstructor
public class ClientServiceImpl extends BaseServiceImpl<ClientMapper, ClientDO, ClientResp, ClientResp, ClientQuery, ClientReq> implements ClientService {

    private final OnlineUserService onlineUserService;

    @Override
    public void beforeAdd(ClientReq req) {
        String clientId = DigestUtil.md5Hex(req.getClientKey() + StringConstants.COLON + req.getClientSecret());
        req.setClientId(clientId);
    }

    @Override
    public void beforeDelete(List<Long> ids) {
        // 如果还存在在线用户，则不能删除
        OnlineUserQuery query = new OnlineUserQuery();
        for (Long id : ids) {
            ClientDO client = this.getById(id);
            query.setClientId(client.getClientId());
            CheckUtils.throwIfNotEmpty(onlineUserService.list(query), "客户端 [{}] 还存在在线用户，不能删除", client.getClientKey());
        }
    }

    @Override
    public ClientResp getByClientId(String clientId) {
        return baseMapper.lambdaQuery()
            .eq(ClientDO::getClientId, clientId)
            .oneOpt()
            .map(client -> BeanUtil.copyProperties(client, ClientResp.class))
            .orElse(null);
    }
}