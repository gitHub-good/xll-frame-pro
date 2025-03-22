package com.xll.frame.starter.system.infrastructure.model.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.io.Serial;
import java.io.Serializable;

@Data
@Schema(description = "修改文件参数")
public class FileReq implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 名称
     */
    @Schema(description = "名称", example = "test123")
    @NotBlank(message = "文件名称不能为空")
    @Length(max = 255, message = "文件名称长度不能超过 {max} 个字符")
    private String name;
}