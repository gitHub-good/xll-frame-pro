package com.xll.frame.starter.extension.crud.mp.model.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 功能描述: <br>
 * <p>
 *  <实体类基类>
 * </p>
 * @author xuliangliang
 * @since 2025/2/16 20:19
 * @version 1.0.0
 */
@Data
public class BaseIdDO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId
    private Long id;
}
