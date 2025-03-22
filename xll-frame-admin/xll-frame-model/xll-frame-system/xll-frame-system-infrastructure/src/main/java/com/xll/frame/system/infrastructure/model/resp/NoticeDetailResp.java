package com.xll.frame.system.infrastructure.model.resp;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.xll.frame.starter.common.model.resp.BaseDetailResp;
import com.xll.frame.system.infrastructure.enums.NoticeScopeEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serial;
import java.time.LocalDateTime;
import java.util.List;

@Data
@ExcelIgnoreUnannotated
@Schema(description = "公告详情信息")
public class NoticeDetailResp extends BaseDetailResp {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 标题
     */
    @Schema(description = "标题", example = "这是公告标题")
    @ExcelProperty(value = "标题")
    private String title;

    /**
     * 内容
     */
    @Schema(description = "内容", example = "这是公告内容")
    @ExcelProperty(value = "内容")
    private String content;

    /**
     * 类型（取值于字典 notice_type）
     */
    @Schema(description = "类型（取值于字典 notice_type）", example = "1")
    @ExcelProperty(value = "类型")
    private String type;

    /**
     * 生效时间
     */
    @Schema(description = "生效时间", example = "2023-08-08 00:00:00", type = "string")
    @ExcelProperty(value = "生效时间")
    private LocalDateTime effectiveTime;

    /**
     * 终止时间
     */
    @Schema(description = "终止时间", example = "2023-08-08 23:59:59", type = "string")
    @ExcelProperty(value = "终止时间")
    private LocalDateTime terminateTime;

    /**
     * 通知范围
     */
    @Schema(description = "通知范围", example = "2")
    private NoticeScopeEnum noticeScope;

    /**
     * 指定用户
     */
    @Schema(description = "指定用户", example = "[1,2,3]")
    private List<String> noticeUsers;
}