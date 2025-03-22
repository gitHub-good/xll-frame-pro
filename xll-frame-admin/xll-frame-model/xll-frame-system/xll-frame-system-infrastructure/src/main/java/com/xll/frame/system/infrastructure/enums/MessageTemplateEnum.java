package com.xll.frame.system.infrastructure.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 功能描述: <br>
 * <p>
 *  <消息模板枚举>
 * </p>
 * @author xuliangliang
 * @since 2025/2/23 18:08
 * @version 1.0.0
 */
@Getter
@RequiredArgsConstructor
public enum MessageTemplateEnum {

    /**
     * 第三方登录
     */
    SOCIAL_REGISTER("欢迎注册 %s", "尊敬的 %s，欢迎注册使用，请及时配置您的密码。");

    private final String title;
    private final String content;
}
