package com.xll.frame.system.infrastructure.auth.model.req;

import cn.hutool.core.lang.RegexPool;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.io.Serial;

/**
 * 功能描述: <br>
 * <p>
 *  <手机号登录参数>
 * </p>
 * @author xuliangliang
 * @since 2025/2/23 17:23
 * @version 1.0.0
 */
@Data
@Schema(description = "手机号登录参数")
public class PhoneLoginReq extends LoginReq {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 手机号
     */
    @Schema(description = "手机号", example = "13811111111")
    @NotBlank(message = "手机号不能为空")
    @Pattern(regexp = RegexPool.MOBILE, message = "手机号格式错误")
    private String phone;

    /**
     * 验证码
     */
    @Schema(description = "验证码", example = "8888")
    @NotBlank(message = "验证码不能为空")
    @Length(max = 4, message = "验证码非法")
    private String captcha;
}
