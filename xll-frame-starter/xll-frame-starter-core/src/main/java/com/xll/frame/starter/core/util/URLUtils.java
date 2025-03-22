package com.xll.frame.starter.core.util;

import cn.hutool.http.HttpUtil;

/**
 * 功能描述: <br>
 * <p>
 *  <统一资源定位符相关工具类>
 * </p>
 * @author xuliangliang
 * @since 2025/2/6 21:05
 * @version 1.0.0
 */
public class URLUtils {

    private URLUtils() {
    }

    /**
     * 提供的 URL 是否为 HTTP URL（协议包括："http"，"https"）
     *
     * @param url URL
     * @return 是否为 HTTP URL
     */
    public static boolean isHttpUrl(String url) {
        return HttpUtil.isHttp(url) || HttpUtil.isHttps(url);
    }
}
