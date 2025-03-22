package com.xll.frame.system.infrastructure.model.resp.dashboard;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "仪表盘-通用图表信息")
public class DashboardChartCommonResp implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 名称
     */
    @Schema(description = "名称", example = "Windows 10")
    private String name;

    /**
     * 数量
     */
    @Schema(description = "数量", example = "1234")
    private Long value;
}
