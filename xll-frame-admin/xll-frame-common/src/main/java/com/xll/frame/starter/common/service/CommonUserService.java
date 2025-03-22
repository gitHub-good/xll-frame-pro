package com.xll.frame.starter.common.service;

import cn.crane4j.annotation.ContainerMethod;
import cn.crane4j.annotation.MappingType;
import com.xll.frame.starter.common.constant.ContainerConstants;

/**
 * 功能描述: <br>
 * <p>
 *  <公共用户业务接口>
 * </p>
 * @author xuliangliang
 * @since 2025/2/23 16:50
 * @version 1.0.0
 */
public interface CommonUserService {

    /**
     * 根据 ID 查询昵称
     *
     * <p>
     * 数据填充容器 {@link ContainerConstants#USER_NICKNAME}
     * </p>
     * 
     * @param id ID
     * @return 昵称
     */
    @ContainerMethod(namespace = ContainerConstants.USER_NICKNAME, type = MappingType.ORDER_OF_KEYS)
    String getNicknameById(Long id);
}
