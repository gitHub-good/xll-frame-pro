package com.xll.frame.starter.captcha.graphic.core;

import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.core.util.ReflectUtil;
import com.wf.captcha.base.Captcha;
import com.xll.frame.starter.captcha.graphic.autoconfigure.GraphicCaptchaProperties;

import java.awt.*;

/**
 * 功能描述: <br>
 * <p>
 *  <图形验证码服务接口>
 * </p>
 * @author xuliangliang
 * @since 2025/3/23 01:43
 * @version 1.0.0
 */
public class GraphicCaptchaService {

    private final GraphicCaptchaProperties properties;

    public GraphicCaptchaService(GraphicCaptchaProperties properties) {
        this.properties = properties;
    }

    /**
     * 获取验证码实例
     *
     * @return 验证码实例
     */
    public Captcha getCaptcha() {
        Captcha captcha = ReflectUtil.newInstance(properties.getType().getCaptchaImpl(), properties
            .getWidth(), properties.getHeight());
        captcha.setLen(properties.getLength());
        if (CharSequenceUtil.isNotBlank(properties.getFontName())) {
            captcha.setFont(new Font(properties.getFontName(), Font.PLAIN, properties.getFontSize()));
        }
        return captcha;
    }
}
