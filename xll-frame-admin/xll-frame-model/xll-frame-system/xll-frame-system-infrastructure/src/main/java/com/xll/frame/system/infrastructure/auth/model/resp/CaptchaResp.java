package com.xll.frame.system.infrastructure.auth.model.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 功能描述: <br>
 * <p>
 *  <验证码信息>
 * </p>
 * @author xuliangliang
 * @since 2025/2/23 17:24
 * @version 1.0.0
 */
@Data
@Builder
@Schema(description = "验证码信息")
public class CaptchaResp implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 验证码标识
     */
    @Schema(description = "验证码标识", example = "090b9a2c-1691-4fca-99db-e4ed0cff362f")
    private String uuid;

    /**
     * 验证码图片（Base64编码，带图片格式：data:image/gif;base64）
     */
    @Schema(description = "验证码图片（Base64编码，带图片格式：data:image/gif;base64）", example = "data:image/png;base64,iVBORw0KGgoAAAAN...")
    private String img;

    /**
     * 过期时间戳
     */
    @Schema(description = "过期时间戳", example = "1714376969409")
    private Long expireTime;

    /**
     * 是否启用
     */
    @Schema(description = "是否启用", example = "true")
    private Boolean isEnabled;

    /**
     * 构建验证码信息
     *
     * @param uuid       验证码标识
     * @param img        验证码图片（Base64编码，带图片格式：data:image/gif;base64）
     * @param expireTime 过期时间戳
     * @return 验证码信息
     */
    public static CaptchaResp of(String uuid, String img, Long expireTime) {
        return CaptchaResp.builder().uuid(uuid).img(img).expireTime(expireTime).isEnabled(true).build();
    }
}
