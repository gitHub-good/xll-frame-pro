package com.xll.frame.starter.extension.data.permission.core.filter;


import com.xll.frame.starter.extension.data.permission.core.model.UserContext;

/**
 * 功能描述: <br>
 * <p>
 *  <数据权限用户上下文提供者>
 * </p>
 * @author xuliangliang
 * @since 2025/2/23 16:26
 * @version 1.0.0
 */
public interface DataPermissionUserContextProvider {

    /**
     * 是否过滤
     *
     * @return true：过滤；false：不过滤
     */
    boolean isFilter();

    /**
     * 获取用户上下文
     *
     * @return 用户上下文
     */
    UserContext getUserContext();
}
