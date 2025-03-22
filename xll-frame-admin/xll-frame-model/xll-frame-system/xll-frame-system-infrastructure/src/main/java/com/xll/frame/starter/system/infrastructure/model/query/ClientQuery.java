package com.xll.frame.starter.system.infrastructure.model.query;

import com.xll.frame.starter.common.enums.DisEnableStatusEnum;
import com.xll.frame.starter.data.core.annotation.Query;
import com.xll.frame.starter.data.core.enums.QueryType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * 功能描述: <br>
 * <p>
 *  <客户端查询条件>
 * </p>
 * @author xuliangliang
 * @since 2025/3/23 00:35
 * @version 1.0.0
 */
@Data
@Schema(description = "客户端查询条件")
public class ClientQuery implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

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
    @Query(type = QueryType.IN)
    private List<String> authType;

    /**
     * 客户端类型
     */
    @Schema(description = "客户端类型", example = "PC")
    private String clientType;

    /**
     * 状态
     */
    @Schema(description = "状态", example = "1")
    private DisEnableStatusEnum status;
}