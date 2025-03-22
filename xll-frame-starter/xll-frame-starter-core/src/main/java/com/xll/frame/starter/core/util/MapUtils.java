package com.xll.frame.starter.core.util;

import java.util.Map;
import java.util.Properties;

/**
 * 功能描述: <br>
 * <p>
 *  <Map 工具类>
 * </p>
 * @author xuliangliang
 * @since 2025/2/6 21:03
 * @version 1.0.0
 */
public class MapUtils {

    private MapUtils() {
    }

    /**
     * 转换为 Properties 对象
     *
     * @param source 数据源
     * @return Properties 对象
     */
    public static Properties toProperties(Map<String, String> source) {
        Properties properties = new Properties();
        properties.putAll(source);
        return properties;
    }
}
