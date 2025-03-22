package com.xll.frame.system.infrastructure.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.xll.frame.starter.common.enums.DataScopeEnum;
import com.xll.frame.starter.common.model.entity.BaseDO;
import com.xll.frame.starter.extension.crud.core.annotation.DictField;
import lombok.Data;

import java.io.Serial;

/**
 * 功能描述: <br>
 * <p>
 *  <角色实体>
 * </p>
 * @author xuliangliang
 * @since 2025/3/23 00:33
 * @version 1.0.0
 */
@Data
@DictField
@TableName("sys_role")
public class RoleDO extends BaseDO {

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
     * 数据权限
     */
    private DataScopeEnum dataScope;

    /**
     * 描述
     */
    private String description;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 是否为系统内置数据
     */
    private Boolean isSystem;

    /**
     * 菜单选择是否父子节点关联
     */
    private Boolean menuCheckStrictly;

    /**
     * 部门选择是否父子节点关联
     */
    private Boolean deptCheckStrictly;
}
