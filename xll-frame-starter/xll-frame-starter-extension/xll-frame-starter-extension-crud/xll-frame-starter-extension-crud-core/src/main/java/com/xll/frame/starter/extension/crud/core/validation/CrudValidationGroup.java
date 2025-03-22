package com.xll.frame.starter.extension.crud.core.validation;

import jakarta.validation.groups.Default;

/**
 * 功能描述: <br>
 * <p>
 *  <CRUD 分组校验>
 * </p>
 * @author xuliangliang
 * @since 2025/2/16 20:11
 * @version 1.0.0
 */
public interface CrudValidationGroup extends Default {

    /**
     * CRUD 分组校验-新增
     */
    interface Add extends CrudValidationGroup {}

    /**
     * CRUD 分组校验-修改
     */
    interface Update extends CrudValidationGroup {}
}
