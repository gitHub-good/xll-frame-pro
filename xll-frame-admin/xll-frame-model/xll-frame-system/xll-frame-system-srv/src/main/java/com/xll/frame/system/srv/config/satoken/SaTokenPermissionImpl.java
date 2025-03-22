package com.xll.frame.system.srv.config.satoken;

import cn.dev33.satoken.stp.StpInterface;
import com.xll.frame.starter.common.context.UserContext;
import com.xll.frame.starter.common.context.UserContextHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能描述: <br>
 * <p>
 *  <Sa-Token 权限认证实现>
 * </p>
 * @author xuliangliang
 * @since 2025/3/23 01:31
 * @version 1.0.0
 */
public class SaTokenPermissionImpl implements StpInterface {

    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        UserContext userContext = UserContextHolder.getContext();
        return new ArrayList<>(userContext.getPermissions());
    }

    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        UserContext userContext = UserContextHolder.getContext();
        return new ArrayList<>(userContext.getRoleCodes());
    }
}
