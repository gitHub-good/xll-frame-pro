package com.xll.frame.starter.system.infrastructure.model.req;

import com.xll.frame.starter.common.enums.DisEnableStatusEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.io.Serial;
import java.io.Serializable;

@Data
@Schema(description = "创建或修改部门参数")
public class DeptReq implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 上级部门 ID
     */
    @Schema(description = "上级部门 ID", example = "2")
    @NotNull(message = "上级部门不能为空")
    private Long parentId;

    /**
     * 名称
     */
    @Schema(description = "名称", example = "测试部")
    @NotBlank(message = "名称不能为空")
    @Length(max = 30, message = "名称长度不能超过 {max} 个字符")
    private String name;

    /**
     * 排序
     */
    @Schema(description = "排序", example = "1")
    @Min(value = 1, message = "排序最小值为 {value}")
    private Integer sort;

    /**
     * 描述
     */
    @Schema(description = "描述", example = "测试部描述信息")
    @Length(max = 200, message = "描述长度不能超过 {max} 个字符")
    private String description;

    /**
     * 状态
     */
    @Schema(description = "状态", example = "1")
    private DisEnableStatusEnum status;

    /**
     * 祖级列表
     */
    @Schema(hidden = true)
    private String ancestors;
}
