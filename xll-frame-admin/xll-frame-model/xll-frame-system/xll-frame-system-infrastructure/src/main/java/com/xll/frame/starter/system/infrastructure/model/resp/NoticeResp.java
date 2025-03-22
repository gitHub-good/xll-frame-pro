package com.xll.frame.starter.system.infrastructure.model.resp;

import com.xll.frame.starter.common.model.resp.BaseResp;
import com.xll.frame.starter.system.infrastructure.enums.NoticeScopeEnum;
import com.xll.frame.starter.system.infrastructure.enums.NoticeStatusEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serial;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Schema(description = "公告信息")
public class NoticeResp extends BaseResp {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 标题
     */
    @Schema(description = "标题", example = "这是公告标题")
    private String title;

    /**
     * 类型（取值于字典 notice_type）
     */
    @Schema(description = "类型（取值于字典 notice_type）", example = "1")
    private String type;

    /**
     * 生效时间
     */
    @Schema(description = "生效时间", example = "2023-08-08 00:00:00", type = "string")
    private LocalDateTime effectiveTime;

    /**
     * 终止时间
     */
    @Schema(description = "终止时间", example = "2023-08-08 23:59:59", type = "string")
    private LocalDateTime terminateTime;

    /**
     * 状态
     *
     * @return 公告状态
     */
    @Schema(description = "状态", example = "1")
    public NoticeStatusEnum getStatus() {
        return NoticeStatusEnum.getStatus(effectiveTime, terminateTime);
    }

    /**
     * 通知范围
     */
    @Schema(description = "通知范围(1.所有人 2.指定用户)", example = "1")
    private NoticeScopeEnum noticeScope;

    /**
     * 指定用户
     */
    @Schema(description = "指定用户", example = "[1,2,3]")
    private List<String> noticeUsers;
}