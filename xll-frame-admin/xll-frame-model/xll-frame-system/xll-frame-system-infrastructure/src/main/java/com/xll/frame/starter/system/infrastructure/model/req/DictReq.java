package com.xll.frame.starter.system.infrastructure.model.req;

import com.xll.frame.starter.common.constant.RegexConstants;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.io.Serial;
import java.io.Serializable;

@Data
@Schema(description = "创建或修改字典参数")
public class DictReq implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 名称
     */
    @Schema(description = "名称", example = "公告类型")
    @NotBlank(message = "名称不能为空")
    @Pattern(regexp = RegexConstants.GENERAL_NAME, message = "名称长度为 2-30 个字符，支持中文、字母、数字、下划线，短横线")
    private String name;

    /**
     * 编码
     */
    @Schema(description = "编码", example = "notice_type")
    @NotBlank(message = "编码不能为空")
    @Pattern(regexp = RegexConstants.GENERAL_CODE, message = "编码长度为 2-30 个字符，支持大小写字母、数字、下划线，以字母开头")
    private String code;

    /**
     * 描述
     */
    @Schema(description = "描述", example = "公告类型描述信息")
    @Length(max = 200, message = "描述长度不能超过 {max} 个字符")
    private String description;
}