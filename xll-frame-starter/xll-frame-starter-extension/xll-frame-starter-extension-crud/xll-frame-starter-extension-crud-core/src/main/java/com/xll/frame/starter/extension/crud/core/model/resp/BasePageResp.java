package com.xll.frame.starter.extension.crud.core.model.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * 功能描述: <br>
 * <p>
 *  <分页信息>
 * </p>
 * @author xuliangliang
 * @since 2025/2/16 20:12
 * @version 1.0.0
 */
@Data
@NoArgsConstructor
@Schema(description = "分页信息")
public class BasePageResp<T> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 列表数据
     */
    @Schema(description = "列表数据")
    private List<T> list;

    /**
     * 总记录数
     */
    @Schema(description = "总记录数", example = "10")
    private long total;

    public BasePageResp(final List<T> list, final long total) {
        this.list = list;
        this.total = total;
    }
}
