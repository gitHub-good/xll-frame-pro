package com.xll.frame.starter.extension.crud.core.model.resp;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 功能描述: <br>
 * <p>
 *  <键值对信息>
 * </p>
 * @author xuliangliang
 * @since 2025/2/16 20:11
 * @version 1.0.0
 */
@Data
@Schema(description = "键值对信息")
public class LabelValueResp<T> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 标签
     */
    @Schema(description = "标签", example = "男")
    private String label;

    /**
     * 值
     */
    @Schema(description = "值", example = "1")
    private T value;

    /**
     * 是否禁用
     */
    @Schema(description = "是否禁用", example = "false")
    private Boolean disabled;

    /**
     * 额外数据
     */
    @Schema(description = "额外数据")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object extra;

    public LabelValueResp() {
    }

    public LabelValueResp(String label, T value) {
        this.label = label;
        this.value = value;
    }

    public LabelValueResp(String label, T value, Object extra) {
        this.label = label;
        this.value = value;
        this.extra = extra;
    }

    public LabelValueResp(String label, T value, Boolean disabled) {
        this.label = label;
        this.value = value;
        this.disabled = disabled;
    }
}
