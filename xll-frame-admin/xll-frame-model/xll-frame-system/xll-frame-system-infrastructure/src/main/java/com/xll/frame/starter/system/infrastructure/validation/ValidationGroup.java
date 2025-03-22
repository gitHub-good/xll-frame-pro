package com.xll.frame.starter.system.infrastructure.validation;

import jakarta.validation.groups.Default;

/**
 * 功能描述: <br>
 * <p>
 *  <分组校验>
 * </p>
 * @author xuliangliang
 * @since 2025/3/23 00:47
 * @version 1.0.0
 */
public interface ValidationGroup extends Default {

    /**
     * 分组校验-增删改查
     */
    interface Storage extends ValidationGroup {
        /**
         * 本地存储
         */
        interface Local extends Storage {
        }

        /**
         * 兼容S3协议存储
         */
        interface S3 extends Storage {
        }
    }
}