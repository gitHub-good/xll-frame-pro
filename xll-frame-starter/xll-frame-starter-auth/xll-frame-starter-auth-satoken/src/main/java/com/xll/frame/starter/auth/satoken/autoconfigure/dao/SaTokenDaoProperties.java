package com.xll.frame.starter.auth.satoken.autoconfigure.dao;

import com.xll.frame.starter.auth.satoken.enums.SaTokenDaoType;

/**
 * 功能描述: <br>
 * <p>
 *  < SaToken 持久层配置属性>
 * </p>
 * @author xuliangliang
 * @since 2025/3/23 02:17
 * @version 1.0.0
 */
public class SaTokenDaoProperties {

    /**
     * 持久层类型
     */
    private SaTokenDaoType type = SaTokenDaoType.DEFAULT;

    public SaTokenDaoType getType() {
        return type;
    }

    public void setType(SaTokenDaoType type) {
        this.type = type;
    }
}
