package com.xll.frame.system.infrastructure.model.resp.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Schema(description = "用户导入结果")
@AllArgsConstructor
@NoArgsConstructor
public class UserImportResp {

    private static final long serialVersionUID = 1L;

    /**
     * 总计行数
     */
    @Schema(description = "总计行数", example = "100")
    private Integer totalRows;

    /**
     * 新增行数
     */
    @Schema(description = "新增行数", example = "100")
    private Integer insertRows;

    /**
     * 修改行数
     */
    @Schema(description = "修改行数", example = "100")
    private Integer updateRows;
}
