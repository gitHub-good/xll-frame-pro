package com.xll.frame.starter.web.autoconfigure.trace;

import lombok.Data;

/**
 * 功能描述: <br>
 * <p>
 *  <TLog 配置属性>
 * </p>
 * @author xuliangliang
 * @since 2025/2/16 18:56
 * @version 1.0.0
 */
@Data
public class TLogProperties {

    /**
     * 日志标签模板
     */
    private String pattern;

    /**
     * 自动打印调用参数和时间
     */
    private Boolean enableInvokeTimePrint;

    /**
     * 自定义 TraceId 生成器
     */
    private String idGenerator;

    /**
     * MDC 模式
     */
    private Boolean mdcEnable;

}
