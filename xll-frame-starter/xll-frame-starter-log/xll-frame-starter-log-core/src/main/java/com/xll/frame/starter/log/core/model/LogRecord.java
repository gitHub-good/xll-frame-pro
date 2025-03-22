package com.xll.frame.starter.log.core.model;

import com.xll.frame.starter.log.core.enums.Include;
import com.xll.frame.starter.log.core.http.RecordableHttpRequest;
import com.xll.frame.starter.log.core.http.RecordableHttpResponse;
import lombok.Data;

import java.time.Duration;
import java.time.Instant;
import java.util.Set;

/**
 * 功能描述: <br>
 * <p>
 *  <日志信息>
 * </p>
 * @author xuliangliang
 * @since 2025/2/23 17:32
 * @version 1.0.0
 */
@Data
public class LogRecord {

    /**
     * 描述
     */
    private String description;

    /**
     * 模块
     */
    private String module;

    /**
     * 请求信息
     */
    private LogRequest request;

    /**
     * 响应信息
     */
    private LogResponse response;

    /**
     * 耗时
     */
    private Duration timeTaken;

    /**
     * 时间戳
     */
    private final Instant timestamp;

    /**
     * 错误信息
     */
    private String errorMsg;

    public LogRecord(Instant timestamp, LogRequest request, LogResponse response, Duration timeTaken) {
        this.timestamp = timestamp;
        this.request = request;
        this.response = response;
        this.timeTaken = timeTaken;
    }

    /**
     * 开始记录日志
     *
     * @param request 请求信息
     * @return 日志记录器
     */
    public static Started start(RecordableHttpRequest request) {
        return start(Instant.now(), request);
    }

    /**
     * 开始记录日志
     *
     * @param timestamp 开始时间
     * @param request   请求信息
     * @return 日志记录器
     */
    public static Started start(Instant timestamp, RecordableHttpRequest request) {
        return new Started(timestamp, request);
    }

    /**
     * 日志记录器
     */
    public static final class Started {

        private final Instant timestamp;

        private final RecordableHttpRequest request;

        private Started(Instant timestamp, RecordableHttpRequest request) {
            this.timestamp = timestamp;
            this.request = request;
        }

        /**
         * 结束日志记录
         *
         * @param timestamp 结束时间
         * @param response  响应信息
         * @param includes  包含信息
         * @return 日志记录
         */
        public LogRecord finish(Instant timestamp, RecordableHttpResponse response, Set<Include> includes) {
            LogRequest logRequest = new LogRequest(this.request, includes);
            LogResponse logResponse = new LogResponse(response, includes);
            Duration duration = Duration.between(this.timestamp, timestamp);
            return new LogRecord(this.timestamp, logRequest, logResponse, duration);
        }
    }

}
