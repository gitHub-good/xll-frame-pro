package com.xll.frame.system.infrastructure.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.xll.frame.starter.common.enums.DisEnableStatusEnum;
import com.xll.frame.starter.common.model.entity.BaseDO;
import lombok.Data;

import java.io.Serial;

/**
 * 功能描述: <br>
 * <p>
 *  <字典项实体>
 * </p>
 * @author xuliangliang
 * @since 2025/3/23 00:32
 * @version 1.0.0
 */
@Data
@TableName("sys_dict_item")
public class DictItemDO extends BaseDO {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 标签
     */
    private String label;

    /**
     * 值
     */
    private String value;

    /**
     * 标签颜色
     */
    private String color;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 描述
     */
    private String description;

    /**
     * 状态
     */
    private DisEnableStatusEnum status;

    /**
     * 字典ID
     */
    private Long dictId;
}