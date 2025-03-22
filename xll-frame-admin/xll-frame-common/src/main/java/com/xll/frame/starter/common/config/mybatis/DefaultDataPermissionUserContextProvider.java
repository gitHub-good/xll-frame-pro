package com.xll.frame.starter.common.config.mybatis;

import cn.hutool.core.convert.Convert;
import com.xll.frame.starter.common.context.UserContextHolder;
import com.xll.frame.starter.extension.data.permission.core.enums.DataScope;
import com.xll.frame.starter.extension.data.permission.core.filter.DataPermissionUserContextProvider;
import com.xll.frame.starter.extension.data.permission.core.model.RoleContext;
import com.xll.frame.starter.extension.data.permission.core.model.UserContext;

import java.util.stream.Collectors;

/**
 * 数据权限用户上下文提供者
 *
 * @author Charles7c
 * @since 2023/12/21 21:19
 */
public class DefaultDataPermissionUserContextProvider implements DataPermissionUserContextProvider {

    @Override
    public boolean isFilter() {
        return !UserContextHolder.isAdmin();
    }

    @Override
    public UserContext getUserContext() {
        com.xll.frame.starter.common.context.UserContext context = UserContextHolder.getContext();
        UserContext userContext = new UserContext();
        userContext.setUserId(Convert.toStr(context.getId()));
        userContext.setDeptId(Convert.toStr(context.getDeptId()));
        userContext.setRoles(context.getRoles()
            .stream()
            .map(r -> new RoleContext(Convert.toStr(r.getId()), DataScope.valueOf(r.getDataScope().name())))
            .collect(Collectors.toSet()));
        return userContext;
    }
}
