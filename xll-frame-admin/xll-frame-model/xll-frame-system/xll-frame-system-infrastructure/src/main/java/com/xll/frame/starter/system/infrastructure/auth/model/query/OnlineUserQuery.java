package com.xll.frame.starter.system.infrastructure.auth.model.query;

import cn.hutool.core.date.DatePattern;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 功能描述: <br>
 * <p>
 *  <在线用户查询条件>
 * </p>
 * @author xuliangliang
 * @since 2025/2/23 17:23
 * @version 1.0.0
 */
@Data
@Schema(description = "在线用户查询条件")
public class OnlineUserQuery implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 用户昵称
     */
    @Schema(description = "用户昵称", example = "张三")
    private String nickname;

    /**
     * 客户端 ID
     */
    @Schema(description = "客户端 ID", example = "ef51c9a3e9046c4f2ea45142c8a8344a")
    private String clientId;

    /**
     * 登录时间
     */
    @Schema(description = "登录时间", example = "2023-08-08 00:00:00,2023-08-08 23:59:59")
    @DateTimeFormat(pattern = DatePattern.NORM_DATETIME_PATTERN)
    private List<Date> loginTime;

    /**
     * 用户 ID
     */
    @Schema(hidden = true)
    private Long userId;

    /**
     * 角色 ID
     */
    @Schema(hidden = true)
    private Long roleId;
}
