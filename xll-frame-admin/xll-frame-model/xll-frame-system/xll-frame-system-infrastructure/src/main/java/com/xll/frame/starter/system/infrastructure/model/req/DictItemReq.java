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
@Schema(description = "创建或修改字典项参数")
public class DictItemReq implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 标签
     */
    @Schema(description = "标签", example = "通知")
    @NotBlank(message = "标签不能为空")
    @Length(max = 30, message = "标签长度不能超过 {max} 个字符")
    private String label;

    /**
     * 值
     */
    @Schema(description = "值", example = "1")
    @NotBlank(message = "值不能为空")
    @Length(max = 30, message = "值长度不能超过 {max} 个字符")
    private String value;

    /**
     * 标签颜色
     */
    @Schema(description = "标签颜色", example = "blue")
    @Length(max = 30, message = "标签颜色长度不能超过 {max} 个字符")
    private String color;

    /**
     * 排序
     */
    @Schema(description = "排序", example = "1")
    @Min(value = 1, message = "排序最小值为 {value}")
    private Integer sort;

    /**
     * 描述
     */
    @Schema(description = "描述", example = "通知描述信息")
    @Length(max = 200, message = "描述长度不能超过 {max} 个字符")
    private String description;

    /**
     * 状态
     */
    @Schema(description = "状态", example = "1")
    private DisEnableStatusEnum status;

    /**
     * 所属字典
     */
    @Schema(description = "所属字典", example = "1")
    @NotNull(message = "所属字典不能为空")
    private Long dictId;
}