package com.xll.frame.starter.system.infrastructure.model.resp;

import com.alibaba.excel.annotation.ExcelProperty;
import com.xll.frame.starter.common.enums.DisEnableStatusEnum;
import com.xll.frame.starter.common.model.resp.BaseDetailResp;
import com.xll.frame.starter.file.excel.converter.ExcelBaseEnumConverter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serial;

@Data
@Schema(description = "字典项信息")
public class DictItemResp extends BaseDetailResp {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 标签
     */
    @Schema(description = "标签", example = "通知")
    private String label;

    /**
     * 值
     */
    @Schema(description = "值", example = "1")
    private String value;

    /**
     * 标签颜色
     */
    @Schema(description = "标签颜色", example = "blue")
    private String color;

    /**
     * 状态
     */
    @Schema(description = "状态", example = "1")
    @ExcelProperty(value = "状态", converter = ExcelBaseEnumConverter.class)
    private DisEnableStatusEnum status;

    /**
     * 排序
     */
    @Schema(description = "排序", example = "1")
    private Integer sort;

    /**
     * 描述
     */
    @Schema(description = "描述", example = "通知描述信息")
    private String description;

    /**
     * 字典 ID
     */
    @Schema(description = "字典 ID", example = "1")
    private Long dictId;
}