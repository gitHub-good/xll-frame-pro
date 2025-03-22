package com.xll.frame.starter.system.infrastructure.model.resp;

import com.xll.frame.starter.common.enums.DisEnableStatusEnum;
import com.xll.frame.starter.common.model.resp.BaseDetailResp;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serial;
import java.util.List;

@Data
@Schema(description = "客户端信息")
public class ClientResp extends BaseDetailResp {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 客户端 ID
     */
    @Schema(description = "客户端 ID", example = "ef51c9a3e9046c4f2ea45142c8a8344a")
    private String clientId;

    /**
     * 客户端 Key
     */
    @Schema(description = "客户端 Key", example = "PC")
    private String clientKey;

    /**
     * 客户端秘钥
     */
    @Schema(description = "客户端秘钥", example = "dd77ab1e353a027e0d60ce3b151e8642")
    private String clientSecret;

    /**
     * 认证类型
     */
    @Schema(description = "认证类型", example = "ACCOUNT")
    private List<String> authType;

    /**
     * 客户端类型
     */
    @Schema(description = "客户端类型", example = "PC")
    private String clientType;

    /**
     * Token 最低活跃频率（单位：秒，-1：不限制，永不冻结）
     */
    @Schema(description = "Token 最低活跃频率（单位：秒，-1：不限制，永不冻结）", example = "1800")
    private Long activeTimeout;

    /**
     * Token 有效期（单位：秒，-1：永不过期）
     */
    @Schema(description = "Token 有效期（单位：秒，-1：永不过期）", example = "86400")
    private Long timeout;

    /**
     * 状态
     */
    @Schema(description = "状态", example = "1")
    private DisEnableStatusEnum status;
}