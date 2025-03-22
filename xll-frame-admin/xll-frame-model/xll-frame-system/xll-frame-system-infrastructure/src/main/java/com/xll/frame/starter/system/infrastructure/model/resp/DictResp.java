package com.xll.frame.starter.system.infrastructure.model.resp;

import com.xll.frame.starter.common.model.resp.BaseDetailResp;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serial;

@Data
@Schema(description = "字典信息")
public class DictResp extends BaseDetailResp {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 名称
     */
    @Schema(description = "名称", example = "公告类型")
    private String name;

    /**
     * 编码
     */
    @Schema(description = "编码", example = "notice_type")
    private String code;

    /**
     * 描述
     */
    @Schema(description = "描述", example = "公告类型描述信息")
    private String description;

    /**
     * 是否为系统内置数据
     */
    @Schema(description = "是否为系统内置数据", example = "true")
    private Boolean isSystem;
}