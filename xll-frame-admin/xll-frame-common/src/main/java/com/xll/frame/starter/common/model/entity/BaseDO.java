package com.xll.frame.starter.common.model.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.xll.frame.starter.extension.crud.mp.model.entity.BaseIdDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.time.LocalDateTime;

/**
 * 功能描述: <br>
 * <p>
 *  <实体类基类>
 * </p>
 * @author xuliangliang
 * @since 2025/2/23 16:49
 * @version 1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BaseDO extends BaseIdDO {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 创建人
     */
    @TableField(fill = FieldFill.INSERT)
    private Long createUser;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 修改人
     */
    @TableField(fill = FieldFill.UPDATE)
    private Long updateUser;

    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;
}
