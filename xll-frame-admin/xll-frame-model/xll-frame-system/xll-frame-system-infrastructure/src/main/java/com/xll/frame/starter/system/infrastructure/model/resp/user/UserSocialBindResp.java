package com.xll.frame.starter.system.infrastructure.model.resp.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
@Schema(description = "第三方账号绑定信息")
public class UserSocialBindResp implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 来源
     */
    @Schema(description = "来源", example = "GITEE")
    private String source;

    /**
     * 描述
     */
    @Schema(description = "描述", example = "码云")
    private String description;
}
