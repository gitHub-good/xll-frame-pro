package com.xll.frame.starter.system.infrastructure.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.xll.frame.starter.common.enums.DisEnableStatusEnum;
import com.xll.frame.starter.common.model.entity.BaseDO;
import lombok.Data;

import java.io.Serial;

/**
 * 功能描述: <br>
 * <p>
 *  <部门实体>
 * </p>
 * @author xuliangliang
 * @since 2025/3/23 00:31
 * @version 1.0.0
 */
@Data
@TableName("sys_dept")
public class DeptDO extends BaseDO {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 名称
     */
    private String name;

    /**
     * 上级部门 ID
     */
    private Long parentId;

    /**
     * 祖级列表
     */
    private String ancestors;

    /**
     * 描述
     */
    private String description;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 状态
     */
    private DisEnableStatusEnum status;

    /**
     * 是否为系统内置数据
     */
    private Boolean isSystem;
}
