package com.xll.frame.system.infrastructure.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.xll.frame.starter.common.enums.DisEnableStatusEnum;
import com.xll.frame.starter.common.model.entity.BaseDO;
import com.xll.frame.starter.security.crypto.annotation.FieldEncrypt;
import com.xll.frame.system.infrastructure.enums.StorageTypeEnum;
import lombok.Data;

import java.io.Serial;

@Data
@TableName("sys_storage")
public class StorageDO extends BaseDO {

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
     * 类型
     */
    private StorageTypeEnum type;

    /**
     * Access Key（访问密钥）
     */
    @FieldEncrypt
    private String accessKey;

    /**
     * Secret Key（私有密钥）
     */
    @FieldEncrypt
    private String secretKey;

    /**
     * Endpoint（终端节点）
     */
    private String endpoint;

    /**
     * 桶名称
     */
    private String bucketName;

    /**
     * 域名
     */
    private String domain;

    /**
     * 描述
     */
    private String description;

    /**
     * 是否为默认存储
     */
    private Boolean isDefault;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 状态
     */
    private DisEnableStatusEnum status;
}