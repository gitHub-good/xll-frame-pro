package com.xll.frame.starter.log.core.model;

import com.xll.frame.starter.log.core.enums.Include;
import com.xll.frame.starter.log.core.http.RecordableHttpResponse;
import lombok.Data;

import java.util.Map;
import java.util.Set;

/**
 * 功能描述: <br>
 * <p>
 *  <响应信息>
 * </p>
 * @author xuliangliang
 * @since 2025/2/23 17:33
 * @version 1.0.0
 */
@Data
public class LogResponse {

    /**
     * 状态码
     */
    private Integer status;

    /**
     * 响应头
     */
    private Map<String, String> headers;

    /**
     * 响应体（JSON 字符串）
     */
    private String body;

    /**
     * 响应参数
     */
    private Map<String, Object> param;

    public LogResponse(RecordableHttpResponse response, Set<Include> includes) {
        this.status = response.getStatus();
        this.headers = (includes.contains(Include.RESPONSE_HEADERS)) ? response.getHeaders() : null;
        if (includes.contains(Include.RESPONSE_BODY)) {
            this.body = response.getBody();
        } else if (includes.contains(Include.RESPONSE_PARAM)) {
            this.param = response.getParam();
        }
    }
}