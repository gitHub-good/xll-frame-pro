package com.xll.frame.system.infrastructure.model.query;

import com.xll.frame.starter.common.enums.DisEnableStatusEnum;
import com.xll.frame.starter.data.core.annotation.Query;
import com.xll.frame.starter.data.core.enums.QueryType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Schema(description = "菜单查询条件")
public class MenuQuery implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 标题
     */
    @Schema(description = "标题", example = "用户管理")
    @Query(type = QueryType.LIKE)
    private String title;

    /**
     * 状态
     */
    @Schema(description = "状态", example = "1")
    private DisEnableStatusEnum status;

    public MenuQuery(DisEnableStatusEnum status) {
        this.status = status;
    }
}
