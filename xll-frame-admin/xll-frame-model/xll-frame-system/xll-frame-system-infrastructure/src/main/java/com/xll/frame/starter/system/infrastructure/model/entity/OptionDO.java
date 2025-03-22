package com.xll.frame.starter.system.infrastructure.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.xll.frame.starter.common.model.entity.BaseUpdateDO;
import lombok.Data;

import java.io.Serial;

/**
 * 功能描述: <br>
 * <p>
 *  <参数实体>
 * </p>
 * @author xuliangliang
 * @since 2025/3/23 00:33
 * @version 1.0.0
 */
@Data
@TableName("sys_option")
public class OptionDO extends BaseUpdateDO {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 类别
     */
    private String category;

    /**
     * 名称
     */
    private String name;

    /**
     * 键
     */
    private String code;

    /**
     * 值
     */
    private String value;

    /**
     * 默认值
     */
    private String defaultValue;

    /**
     * 描述
     */
    private String description;
}
