//package com.xll.frame.starter.system.application.handle;
//
//import cn.dev33.satoken.stp.StpUtil;
//import cn.hutool.core.bean.BeanUtil;
//import cn.hutool.core.collection.CollUtil;
//import cn.hutool.core.util.IdUtil;
//import cn.hutool.core.util.RandomUtil;
//import cn.hutool.core.util.ReUtil;
//import cn.hutool.json.JSONUtil;
//import com.xll.frame.starter.common.constant.RegexConstants;
//import com.xll.frame.starter.common.constant.SysConstants;
//import com.xll.frame.starter.common.enums.DisEnableStatusEnum;
//import com.xll.frame.starter.common.enums.GenderEnum;
//import com.xll.frame.starter.core.autoconfigure.project.ProjectProperties;
//import com.xll.frame.starter.core.validation.ValidationUtils;
//import com.xll.frame.starter.mns.websocket.util.WebSocketUtils;
//import com.xll.frame.starter.system.application.AbstractLoginHandler;
//import com.xll.frame.starter.system.domain.system.MessageService;
//import com.xll.frame.starter.system.domain.system.UserRoleService;
//import com.xll.frame.starter.system.domain.system.UserSocialService;
//import com.xll.frame.starter.system.infrastructure.auth.enums.AuthTypeEnum;
//import com.xll.frame.starter.system.infrastructure.auth.model.req.SocialLoginReq;
//import com.xll.frame.starter.system.infrastructure.auth.model.resp.LoginResp;
//import com.xll.frame.starter.system.infrastructure.enums.MessageTemplateEnum;
//import com.xll.frame.starter.system.infrastructure.enums.MessageTypeEnum;
//import com.xll.frame.starter.system.infrastructure.model.entity.RoleDO;
//import com.xll.frame.starter.system.infrastructure.model.entity.UserDO;
//import com.xll.frame.starter.system.infrastructure.model.entity.UserSocialDO;
//import com.xll.frame.starter.system.infrastructure.model.req.MessageReq;
//import com.xll.frame.starter.system.infrastructure.model.resp.ClientResp;
//import jakarta.servlet.http.HttpServletRequest;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Component;
//
//import java.time.LocalDateTime;
//import java.util.Collections;
//import java.util.List;
//
///**
// * 功能描述: <br>
// * <p>
// *  <第三方账号登录处理器>
// * </p>
// * @author xuliangliang
// * @since 2025/3/23 01:20
// * @version 1.0.0
// */
//@Component
//@RequiredArgsConstructor
//public class SocialLoginHandler extends AbstractLoginHandler<SocialLoginReq> {
//
//    private final AuthRequestFactory authRequestFactory;
//    private final UserSocialService userSocialService;
//    private final UserRoleService userRoleService;
//    private final MessageService messageService;
//    private final ProjectProperties projectProperties;
//
//    @Override
//    public LoginResp login(SocialLoginReq req, ClientResp client, HttpServletRequest request) {
//        // 获取第三方登录信息
//        AuthRequest authRequest = this.getAuthRequest(req.getSource());
//        AuthCallback callback = new AuthCallback();
//        callback.setCode(req.getCode());
//        callback.setState(req.getState());
//        AuthResponse<AuthUser> response = authRequest.login(callback);
//        ValidationUtils.throwIf(!response.ok(), response.getMsg());
//        AuthUser authUser = response.getData();
//        // 如未绑定则自动注册新用户，保存或更新关联信息
//        String source = authUser.getSource();
//        String openId = authUser.getUuid();
//        UserSocialDO userSocial = userSocialService.getBySourceAndOpenId(source, openId);
//        UserDO user;
//        if (null == userSocial) {
//            String username = authUser.getUsername();
//            String nickname = authUser.getNickname();
//            UserDO existsUser = userService.getByUsername(username);
//            String randomStr = RandomUtil.randomString(RandomUtil.BASE_CHAR, 5);
//            if (null != existsUser || !ReUtil.isMatch(RegexConstants.USERNAME, username)) {
//                username = randomStr + IdUtil.fastSimpleUUID();
//            }
//            if (!ReUtil.isMatch(RegexConstants.GENERAL_NAME, nickname)) {
//                nickname = source.toLowerCase() + randomStr;
//            }
//            user = new UserDO();
//            user.setUsername(username);
//            user.setNickname(nickname);
//            user.setGender(GenderEnum.valueOf(authUser.getGender().name()));
//            user.setAvatar(authUser.getAvatar());
//            user.setDeptId(SysConstants.SUPER_DEPT_ID);
//            user.setStatus(DisEnableStatusEnum.ENABLE);
//            userService.save(user);
//            Long userId = user.getId();
//            RoleDO role = roleService.getByCode(SysConstants.SUPER_ROLE_CODE);
//            userRoleService.assignRolesToUser(Collections.singletonList(role.getId()), userId);
//            userSocial = new UserSocialDO();
//            userSocial.setUserId(userId);
//            userSocial.setSource(source);
//            userSocial.setOpenId(openId);
//            this.sendSecurityMsg(user);
//        } else {
//            user = BeanUtil.copyProperties(userService.getById(userSocial.getUserId()), UserDO.class);
//        }
//        // 检查用户状态
//        super.checkUserStatus(user);
//        userSocial.setMetaJson(JSONUtil.toJsonStr(authUser));
//        userSocial.setLastLoginTime(LocalDateTime.now());
//        userSocialService.saveOrUpdate(userSocial);
//        // 执行认证
//        String token = super.authenticate(user, client);
//        return LoginResp.builder().token(token).build();
//    }
//
//    @Override
//    public void preLogin(SocialLoginReq req, ClientResp client, HttpServletRequest request) {
//        super.preLogin(req, client, request);
//        if (StpUtil.isLogin()) {
//            StpUtil.logout();
//        }
//    }
//
//    @Override
//    public AuthTypeEnum getAuthType() {
//        return AuthTypeEnum.SOCIAL;
//    }
//
//    /**
//     * 获取 AuthRequest
//     *
//     * @param source 平台名称
//     * @return AuthRequest
//     */
//    private AuthRequest getAuthRequest(String source) {
//        try {
//            return authRequestFactory.get(source);
//        } catch (Exception e) {
//            throw new BadRequestException("暂不支持 [%s] 平台账号登录".formatted(source));
//        }
//    }
//
//    /**
//     * 发送安全消息
//     *
//     * @param user 用户信息
//     */
//    private void sendSecurityMsg(UserDO user) {
//        MessageReq req = new MessageReq();
//        MessageTemplateEnum socialRegister = MessageTemplateEnum.SOCIAL_REGISTER;
//        req.setTitle(socialRegister.getTitle().formatted(projectProperties.getName()));
//        req.setContent(socialRegister.getContent().formatted(user.getNickname()));
//        req.setType(MessageTypeEnum.SECURITY);
//        messageService.add(req, CollUtil.toList(user.getId()));
//        List<String> tokenList = StpUtil.getTokenValueListByLoginId(user.getId());
//        for (String token : tokenList) {
//            WebSocketUtils.sendMessage(token, "1");
//        }
//    }
//}
