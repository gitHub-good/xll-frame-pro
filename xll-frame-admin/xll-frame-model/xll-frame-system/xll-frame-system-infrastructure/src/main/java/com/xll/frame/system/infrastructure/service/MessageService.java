package com.xll.frame.system.infrastructure.service;

import com.xll.frame.starter.extension.crud.core.model.query.PageQuery;
import com.xll.frame.starter.extension.crud.mp.model.resp.PageResp;
import com.xll.frame.system.infrastructure.model.query.MessageQuery;
import com.xll.frame.system.infrastructure.model.req.MessageReq;
import com.xll.frame.system.infrastructure.model.resp.MessageResp;

import java.util.List;

/**
 * 功能描述: <br>
 * <p>
 *  <消息业务接口>
 * </p>
 * @author xuliangliang
 * @since 2025/3/23 01:10
 * @version 1.0.0
 */
public interface MessageService {

    /**
     * 分页查询列表
     *
     * @param query     查询条件
     * @param pageQuery 分页查询条件
     * @return 分页列表信息
     */
    PageResp<MessageResp> page(MessageQuery query, PageQuery pageQuery);

    /**
     * 新增
     *
     * @param req        新增信息
     * @param userIdList 接收人列表
     */
    void add(MessageReq req, List<Long> userIdList);

    /**
     * 删除
     *
     * @param ids ID 列表
     */
    void delete(List<Long> ids);
}