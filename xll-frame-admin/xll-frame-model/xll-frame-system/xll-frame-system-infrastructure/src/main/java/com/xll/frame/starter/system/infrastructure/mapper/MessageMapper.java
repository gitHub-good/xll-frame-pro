package com.xll.frame.starter.system.infrastructure.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.xll.frame.starter.data.mp.base.BaseMapper;
import com.xll.frame.starter.system.infrastructure.model.entity.MessageDO;
import com.xll.frame.starter.system.infrastructure.model.resp.MessageResp;
import org.apache.ibatis.annotations.Param;

/**
 * 功能描述: <br>
 * <p>
 *  <消息 Mapper>
 * </p>
 * @author xuliangliang
 * @since 2025/3/23 00:29
 * @version 1.0.0
 */
public interface MessageMapper extends BaseMapper<MessageDO> {

    /**
     * 分页查询列表
     *
     * @param page         分页查询条件
     * @param queryWrapper 查询条件
     * @return 分页信息
     */
    IPage<MessageResp> selectPageByUserId(@Param("page") IPage<Object> page,
                                          @Param(Constants.WRAPPER) QueryWrapper<MessageDO> queryWrapper);
}