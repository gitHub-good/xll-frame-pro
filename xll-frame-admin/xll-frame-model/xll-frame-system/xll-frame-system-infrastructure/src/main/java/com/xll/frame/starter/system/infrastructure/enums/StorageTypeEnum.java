package com.xll.frame.starter.system.infrastructure.enums;

import com.xll.frame.starter.core.enums.BaseEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
/**
 * 功能描述: <br>
 * <p>
 *  <存储类型枚举>
 * </p>
 * @author xuliangliang
 * @since 2025/2/23 18:11
 * @version 1.0.0
 */
@Getter
@RequiredArgsConstructor
public enum StorageTypeEnum implements BaseEnum<Integer> {

    /**
     * 兼容S3协议存储
     */
    S3(1, "兼容S3协议存储"),

    /**
     * 本地存储
     */
    LOCAL(2, "本地存储"),;

    private final Integer value;
    private final String description;
}
