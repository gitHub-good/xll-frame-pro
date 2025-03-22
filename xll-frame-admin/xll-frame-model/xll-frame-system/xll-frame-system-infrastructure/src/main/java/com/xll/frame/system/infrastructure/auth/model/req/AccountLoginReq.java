package com.xll.frame.system.infrastructure.auth.model.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serial;

/**
 * 功能描述: <br>
 * <p>
 *  <账号登录参数>
 * </p>
 * @author xuliangliang
 * @since 2025/2/23 17:23
 * @version 1.0.0
 */
@Data
@Schema(description = "账号登录参数")
public class AccountLoginReq extends LoginReq {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 用户名
     */
    @Schema(description = "用户名", example = "zhangsan")
    @NotBlank(message = "用户名不能为空")
    private String username;

    /**
     * 密码（加密）
     */
    @Schema(description = "密码（加密）", example = "HHwZoiBwCfh0xLdWOAd0bHOkEZlIMMOQKJyeFUw9T3ArrhL57od2i42s1o0sSXKkeHPJXvQsninhPFH2lArDDQ==")
    @NotBlank(message = "密码不能为空")
    private String password;

    /**
     * 验证码
     */
    @Schema(description = "验证码", example = "ABCD")
    private String captcha;

    /**
     * 验证码标识
     */
    @Schema(description = "验证码标识", example = "090b9a2c-1691-4fca-99db-e4ed0cff362f")
    private String uuid;
}
