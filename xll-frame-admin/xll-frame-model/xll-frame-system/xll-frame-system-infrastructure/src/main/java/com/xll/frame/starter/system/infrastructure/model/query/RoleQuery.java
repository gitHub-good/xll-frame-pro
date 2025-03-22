package com.xll.frame.starter.system.infrastructure.model.query;

import com.xll.frame.starter.data.core.annotation.Query;
import com.xll.frame.starter.data.core.enums.QueryType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
@Schema(description = "角色查询条件")
public class RoleQuery implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 关键词
     */
    @Schema(description = "关键词", example = "测试人员")
    @Query(columns = {"name", "code", "description"}, type = QueryType.LIKE)
    private String description;
}
