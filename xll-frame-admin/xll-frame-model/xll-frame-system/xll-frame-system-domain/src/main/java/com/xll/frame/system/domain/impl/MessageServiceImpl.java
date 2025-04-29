package com.xll.frame.system.domain.impl;

import cn.crane4j.annotation.AutoOperate;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xll.frame.starter.core.validation.CheckUtils;
import com.xll.frame.starter.data.mp.util.QueryWrapperHelper;
import com.xll.frame.starter.extension.crud.core.model.query.PageQuery;
import com.xll.frame.starter.extension.crud.mp.model.resp.PageResp;
import com.xll.frame.system.infrastructure.service.MessageService;
import com.xll.frame.system.infrastructure.service.MessageUserService;
import com.xll.frame.system.infrastructure.model.entity.MessageDO;
import com.xll.frame.system.infrastructure.model.query.MessageQuery;
import com.xll.frame.system.infrastructure.model.req.MessageReq;
import com.xll.frame.system.infrastructure.model.resp.MessageResp;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.xll.frame.system.infrastructure.mapper.MessageMapper;

import java.util.List;

/**
 * 功能描述: <br>
 * <p>
 *  <消息业务实现>
 * </p>
 * @author xuliangliang
 * @since 2025/3/23 01:10
 * @version 1.0.0
 */
@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageMapper baseMapper;
    private final MessageUserService messageUserService;

    @Override
    @AutoOperate(type = MessageResp.class, on = "list")
    public PageResp<MessageResp> page(MessageQuery query, PageQuery pageQuery) {
        QueryWrapper<MessageDO> queryWrapper = QueryWrapperHelper.build(query, pageQuery.getSort());
        queryWrapper.apply(null != query.getUserId(), "t2.user_id={0}", query.getUserId())
            .apply(null != query.getIsRead(), "t2.is_read={0}", query.getIsRead());
        IPage<MessageResp> page = baseMapper.selectPageByUserId(new Page<>(pageQuery.getPage(), pageQuery
            .getSize()), queryWrapper);
        return PageResp.build(page);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(MessageReq req, List<Long> userIdList) {
        CheckUtils.throwIf(() -> CollUtil.isEmpty(userIdList), "消息接收人不能为空");
        MessageDO message = BeanUtil.copyProperties(req, MessageDO.class);
        baseMapper.insert(message);
        messageUserService.add(message.getId(), userIdList);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(List<Long> ids) {
        baseMapper.deleteByIds(ids);
        messageUserService.deleteByMessageIds(ids);
    }
}