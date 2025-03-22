package com.xll.frame.starter.system.application;

import cn.dev33.satoken.stp.SaLoginModel;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import com.xll.frame.starter.common.context.UserContextHolder;
import com.xll.frame.starter.common.context.UserExtraContext;
import com.xll.frame.starter.common.enums.DisEnableStatusEnum;
import com.xll.frame.starter.core.validation.CheckUtils;
import com.xll.frame.starter.core.validation.Validator;
import com.xll.frame.starter.system.domain.system.DeptService;
import com.xll.frame.starter.system.domain.system.OptionService;
import com.xll.frame.starter.system.domain.system.RoleService;
import com.xll.frame.starter.system.domain.system.UserService;
import com.xll.frame.starter.system.infrastructure.auth.LoginHandler;
import com.xll.frame.starter.system.infrastructure.auth.model.req.LoginReq;
import com.xll.frame.starter.system.infrastructure.model.entity.DeptDO;
import com.xll.frame.starter.system.infrastructure.model.entity.UserDO;
import com.xll.frame.starter.system.infrastructure.model.resp.ClientResp;
import com.xll.frame.starter.web.util.SpringWebUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

/**
 * 登录处理器基类
 *
 * @author KAI
 * @author Charles7c
 * @since 2024/12/22 14:52
 */
@Component
public abstract class AbstractLoginHandler<T extends LoginReq> implements LoginHandler<T> {

    @Resource
    protected OptionService optionService;
    @Resource
    protected UserService userService;
    @Resource
    protected RoleService roleService;
    @Resource
    private DeptService deptService;
    @Resource
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    protected static final String CAPTCHA_EXPIRED = "验证码已失效";
    protected static final String CAPTCHA_ERROR = "验证码错误";
    protected static final String CLIENT_ID = "clientId";

    @Override
    public void preLogin(T req, ClientResp client, HttpServletRequest request) {
        // 参数校验
        Validator.validate(req);
    }

    @Override
    public void postLogin(T req, ClientResp client, HttpServletRequest request) {
    }

    /**
     * 认证
     *
     * @param user   用户信息
     * @param client 客户端信息
     * @return token 令牌信息
     */
    protected String authenticate(UserDO user, ClientResp client) {
        // 获取权限、角色、密码过期天数
        Long userId = user.getId();
        CompletableFuture<Set<String>> permissionFuture = CompletableFuture.supplyAsync(() -> roleService
            .listPermissionByUserId(userId), threadPoolTaskExecutor);
        CompletableFuture<Set<RoleContext>> roleFuture = CompletableFuture.supplyAsync(() -> roleService
            .listByUserId(userId), threadPoolTaskExecutor);
        CompletableFuture<Integer> passwordExpirationDaysFuture = CompletableFuture.supplyAsync(() -> optionService
            .getValueByCode2Int(PASSWORD_EXPIRATION_DAYS.name()));
        CompletableFuture.allOf(permissionFuture, roleFuture, passwordExpirationDaysFuture);
        UserContext userContext = new UserContext(permissionFuture.join(), roleFuture
            .join(), passwordExpirationDaysFuture.join());
        BeanUtil.copyProperties(user, userContext);
        // 设置登录配置参数
        SaLoginModel model = new SaLoginModel();
        model.setActiveTimeout(client.getActiveTimeout());
        model.setTimeout(client.getTimeout());
        model.setDevice(client.getClientType());
        userContext.setClientType(client.getClientType());
        model.setExtra(CLIENT_ID, client.getClientId());
        userContext.setClientId(client.getClientId());
        // 登录并缓存用户信息
        StpUtil.login(userContext.getId(), model.setExtraData(BeanUtil.beanToMap(new UserExtraContext(SpringWebUtils
            .getRequest()))));
        UserContextHolder.setContext(userContext);
        return StpUtil.getTokenValue();
    }

    /**
     * 检查用户状态
     *
     * @param user 用户信息
     */
    protected void checkUserStatus(UserDO user) {
        CheckUtils.throwIfEqual(DisEnableStatusEnum.DISABLE, user.getStatus(), "此账号已被禁用，如有疑问，请联系管理员");
        DeptDO dept = deptService.getById(user.getDeptId());
        CheckUtils.throwIfEqual(DisEnableStatusEnum.DISABLE, dept.getStatus(), "此账号所属部门已被禁用，如有疑问，请联系管理员");
    }
}