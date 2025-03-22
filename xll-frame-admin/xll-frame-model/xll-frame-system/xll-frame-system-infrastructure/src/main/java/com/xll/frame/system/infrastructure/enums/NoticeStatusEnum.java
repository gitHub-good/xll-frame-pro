package com.xll.frame.system.infrastructure.enums;

import com.xll.frame.starter.common.constant.UiConstants;
import com.xll.frame.starter.core.enums.BaseEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

/**
 * 功能描述: <br>
 * <p>
 *  <公告状态枚举>
 * </p>
 * @author xuliangliang
 * @since 2025/2/23 18:09
 * @version 1.0.0
 */
@Getter
@RequiredArgsConstructor
public enum NoticeStatusEnum implements BaseEnum<Integer> {

    /**
     * 待发布
     */
    PENDING_RELEASE(1, "待发布", UiConstants.COLOR_PRIMARY),

    /**
     * 已发布
     */
    PUBLISHED(2, "已发布", UiConstants.COLOR_SUCCESS),

    /**
     * 已过期
     */
    EXPIRED(3, "已过期", UiConstants.COLOR_ERROR),;

    private final Integer value;
    private final String description;
    private final String color;

    /**
     * 获取公告状态
     *
     * @param effectiveTime 生效时间
     * @param terminateTime 终止时间
     * @return 公告状态
     */
    public static NoticeStatusEnum getStatus(LocalDateTime effectiveTime, LocalDateTime terminateTime) {
        LocalDateTime now = LocalDateTime.now();
        if (effectiveTime != null && effectiveTime.isAfter(now)) {
            return PENDING_RELEASE;
        }
        if (terminateTime != null && terminateTime.isBefore(now)) {
            return EXPIRED;
        }
        return PUBLISHED;
    }
}
