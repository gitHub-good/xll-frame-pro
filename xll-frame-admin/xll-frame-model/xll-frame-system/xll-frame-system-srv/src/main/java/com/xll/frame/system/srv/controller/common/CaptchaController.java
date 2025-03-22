package com.xll.frame.system.srv.controller.common;

import cn.dev33.satoken.annotation.SaIgnore;
import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.util.IdUtil;
import com.wf.captcha.base.Captcha;
import com.xll.frame.starter.cache.redisson.util.RedisUtils;
import com.xll.frame.starter.captcha.graphic.core.GraphicCaptchaService;
import com.xll.frame.starter.common.config.properties.CaptchaProperties;
import com.xll.frame.starter.common.constant.CacheConstants;
import com.xll.frame.starter.common.constant.SysConstants;
import com.xll.frame.starter.log.core.annotation.Log;
import com.xll.frame.system.domain.OptionService;
import com.xll.frame.system.infrastructure.auth.model.resp.CaptchaResp;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.time.LocalDateTime;

@Tag(name = "验证码 API")
@SaIgnore
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/captcha")
public class CaptchaController {
    private final CaptchaProperties captchaProperties;
    private final GraphicCaptchaService graphicCaptchaService;
    private final OptionService optionService;

    @Log(ignore = true)
    @Operation(summary = "获取图片验证码", description = "获取图片验证码（Base64编码，带图片格式：data:image/gif;base64）")
    @GetMapping("/image")
    public CaptchaResp getImageCaptcha() {
        int loginCaptchaEnabled = optionService.getValueByCode2Int("LOGIN_CAPTCHA_ENABLED");
        if (SysConstants.NO.equals(loginCaptchaEnabled)) {
            return CaptchaResp.builder().isEnabled(false).build();
        }
        String uuid = IdUtil.fastUUID();
        String captchaKey = CacheConstants.CAPTCHA_KEY_PREFIX + uuid;
        Captcha captcha = graphicCaptchaService.getCaptcha();
        long expireTime = LocalDateTimeUtil.toEpochMilli(LocalDateTime.now()
            .plusMinutes(captchaProperties.getExpirationInMinutes()));
        RedisUtils.set(captchaKey, captcha.text(), Duration.ofMinutes(captchaProperties.getExpirationInMinutes()));
        return CaptchaResp.of(uuid, captcha.toBase64(), expireTime);
    }
}
