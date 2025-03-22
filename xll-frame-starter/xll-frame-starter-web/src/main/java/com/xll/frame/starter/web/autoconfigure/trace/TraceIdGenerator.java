package com.xll.frame.starter.web.autoconfigure.trace;

import com.yomahub.tlog.id.TLogIdGenerator;
import com.yomahub.tlog.id.snowflake.UniqueIdGenerator;

/**
 * 功能描述: <br>
 * <p>
 *  <TLog ID 生成器>
 * </p>
 * @author xuliangliang
 * @since 2025/2/16 18:57
 * @version 1.0.0
 */
public class TraceIdGenerator extends TLogIdGenerator {
    @Override
    public String generateTraceId() {
        return String.valueOf(UniqueIdGenerator.generateId());
    }
}