package com.xll.frame.system.infrastructure.model.query;

import com.xll.frame.starter.data.core.annotation.Query;
import com.xll.frame.starter.data.core.enums.QueryType;
import com.xll.frame.system.infrastructure.enums.FileTypeEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
@Schema(description = "文件查询条件")
public class FileQuery implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 名称
     */
    @Schema(description = "名称", example = "图片")
    @Query(type = QueryType.LIKE)
    private String name;

    /**
     * 类型
     */
    @Schema(description = "类型", example = "2")
    private FileTypeEnum type;
}