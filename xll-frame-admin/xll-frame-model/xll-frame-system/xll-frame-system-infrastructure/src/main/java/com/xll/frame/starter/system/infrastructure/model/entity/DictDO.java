package com.xll.frame.starter.system.infrastructure.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.xll.frame.starter.common.model.entity.BaseDO;
import com.xll.frame.starter.extension.crud.core.annotation.DictField;
import lombok.Data;

import java.io.Serial;

/**
 * 功能描述: <br>
 * <p>
 *  <字典实体>
 * </p>
 * @author xuliangliang
 * @since 2025/3/23 00:32
 * @version 1.0.0
 */
@Data
@DictField(valueKey = "code")
@TableName("sys_dict")
public class DictDO extends BaseDO {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 名称
     */
    private String name;

    /**
     * 编码
     */
    private String code;

    /**
     * 描述
     */
    private String description;

    /**
     * 是否为系统内置数据
     */
    private Boolean isSystem;
}