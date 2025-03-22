package com.xll.frame.starter.system.infrastructure.auth.model.req;

import cn.hutool.core.lang.RegexPool;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import java.io.Serial;

/**
 * 功能描述: <br>
 * <p>
 *  <邮箱登录参数>
 * </p>
 * @author xuliangliang
 * @since 2025/2/23 17:23
 * @version 1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Schema(description = "邮箱登录参数")
public class EmailLoginReq extends LoginReq {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 邮箱
     */
    @Schema(description = "邮箱", example = "123456789@qq.com")
    @NotBlank(message = "邮箱不能为空")
    @Pattern(regexp = RegexPool.EMAIL, message = "邮箱格式错误")
    private String email;

    /**
     * 验证码
     */
    @Schema(description = "验证码", example = "888888")
    @NotBlank(message = "验证码不能为空")
    @Length(max = 6, message = "验证码非法")
    private String captcha;
}
