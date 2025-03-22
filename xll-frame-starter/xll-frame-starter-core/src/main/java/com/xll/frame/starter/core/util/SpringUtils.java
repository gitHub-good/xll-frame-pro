package com.xll.frame.starter.core.util;

import cn.hutool.extra.spring.SpringUtil;

/**
 * 功能描述: <br>
 * <p>
 *  <Spring 工具类>
 * </p>
 * @author xuliangliang
 * @since 2025/2/6 21:04
 * @version 1.0.0
 */
public class SpringUtils {

    private SpringUtils() {
    }

    /**
     * 获取代理对象
     *
     * @param target 目标对象
     * @param <T>    目标对象类型
     * @return 代理对象
     * @since 2.8.2
     */
    @SuppressWarnings("unchecked")
    public static <T> T getProxy(T target) {
        return (T)SpringUtil.getBean(target.getClass());
    }
}
