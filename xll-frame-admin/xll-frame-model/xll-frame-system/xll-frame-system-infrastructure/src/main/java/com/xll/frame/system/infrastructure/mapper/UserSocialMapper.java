package com.xll.frame.system.infrastructure.mapper;

import com.xll.frame.starter.data.mp.base.BaseMapper;
import com.xll.frame.system.infrastructure.model.entity.UserSocialDO;
import org.apache.ibatis.annotations.Param;

/**
 * 功能描述: <br>
 * <p>
 *  <用户社会化关联 Mapper>
 * </p>
 * @author xuliangliang
 * @since 2025/3/23 00:31
 * @version 1.0.0
 */
public interface UserSocialMapper extends BaseMapper<UserSocialDO> {

    /**
     * 根据来源和开放 ID 查询
     *
     * @param source 来源
     * @param openId 开放 ID
     * @return 用户社会化关联信息
     */
    UserSocialDO selectBySourceAndOpenId(@Param("source") String source, @Param("openId") String openId);
}
