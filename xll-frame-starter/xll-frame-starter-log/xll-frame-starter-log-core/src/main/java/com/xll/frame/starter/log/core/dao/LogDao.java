package com.xll.frame.starter.log.core.dao;

import com.xll.frame.starter.log.core.model.LogRecord;

import java.util.Collections;
import java.util.List;

/**
 * 功能描述: <br>
 * <p>
 *  <日志持久层接口>
 * </p>
 * @author xuliangliang
 * @since 2025/2/23 17:30
 * @version 1.0.0
 */
public interface LogDao {

    /**
     * 查询日志列表
     *
     * @return 日志列表
     */
    default List<LogRecord> list() {
        return Collections.emptyList();
    }

    /**
     * 记录日志
     *
     * @param logRecord 日志信息
     */
    void add(LogRecord logRecord);
}
