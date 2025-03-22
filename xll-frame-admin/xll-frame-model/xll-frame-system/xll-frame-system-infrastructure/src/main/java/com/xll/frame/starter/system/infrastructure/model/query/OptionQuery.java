package com.xll.frame.starter.system.infrastructure.model.query;

import com.xll.frame.starter.core.validation.constraints.EnumValue;
import com.xll.frame.starter.data.core.annotation.Query;
import com.xll.frame.starter.data.core.enums.QueryType;
import com.xll.frame.starter.system.infrastructure.enums.OptionCategoryEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
@Schema(description = "参数查询条件")
public class OptionQuery implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 键列表
     */
    @Schema(description = "键列表", example = "SITE_TITLE,SITE_COPYRIGHT")
    @Query(type = QueryType.IN)
    private List<String> code;

    /**
     * 类别
     */
    @Schema(description = "类别", example = "SITE")
    @EnumValue(value = OptionCategoryEnum.class, message = "类别非法")
    private String category;
}