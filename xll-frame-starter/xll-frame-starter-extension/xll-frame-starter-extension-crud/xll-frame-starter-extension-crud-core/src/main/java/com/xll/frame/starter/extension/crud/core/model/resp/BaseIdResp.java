package com.xll.frame.starter.extension.crud.core.model.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * 功能描述: <br>
 * <p>
 *  <ID 响应信息>
 * </p>
 * @author xuliangliang
 * @since 2025/2/16 20:10
 * @version 1.0.0
 */
@Data
public class BaseIdResp<T extends Serializable> implements Serializable {

    /**
     * ID
     */
    @Schema(description = "ID", example = "1")
    private T id;

    public BaseIdResp() {
    }

    public BaseIdResp(final T id) {
        this.id = id;
    }
}
