package com.xll.frame.system.application.handle;

import com.xll.frame.starter.cache.redisson.util.RedisUtils;
import com.xll.frame.starter.common.constant.CacheConstants;
import com.xll.frame.starter.core.validation.ValidationUtils;
import com.xll.frame.system.application.AbstractLoginHandler;
import com.xll.frame.system.infrastructure.auth.enums.AuthTypeEnum;
import com.xll.frame.system.infrastructure.auth.model.req.PhoneLoginReq;
import com.xll.frame.system.infrastructure.auth.model.resp.LoginResp;
import com.xll.frame.system.infrastructure.model.entity.UserDO;
import com.xll.frame.system.infrastructure.model.resp.ClientResp;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

/**
 * 功能描述: <br>
 * <p>
 *  <手机号登录处理器>
 * </p>
 * @author xuliangliang
 * @since 2025/3/23 01:19
 * @version 1.0.0
 */
@Component
public class PhoneLoginHandler extends AbstractLoginHandler<PhoneLoginReq> {

    @Override
    public LoginResp login(PhoneLoginReq req, ClientResp client, HttpServletRequest request) {
        // 验证手机号
        UserDO user = userService.getByPhone(req.getPhone());
        ValidationUtils.throwIfNull(user, "此手机号未绑定本系统账号");
        // 检查用户状态
        super.checkUserStatus(user);
        // 执行认证
        String token = super.authenticate(user, client);
        return LoginResp.builder().token(token).build();
    }

    @Override
    public void preLogin(PhoneLoginReq req, ClientResp client, HttpServletRequest request) {
        String phone = req.getPhone();
        String captchaKey = CacheConstants.CAPTCHA_KEY_PREFIX + phone;
        String captcha = RedisUtils.get(captchaKey);
        ValidationUtils.throwIfBlank(captcha, CAPTCHA_EXPIRED);
        ValidationUtils.throwIfNotEqualIgnoreCase(req.getCaptcha(), captcha, CAPTCHA_ERROR);
        RedisUtils.delete(captchaKey);
    }

    @Override
    public AuthTypeEnum getAuthType() {
        return AuthTypeEnum.PHONE;
    }
}