package com.xll.frame.starter.log.core;

import com.xll.frame.starter.log.core.enums.Include;
import com.xll.frame.starter.log.core.model.LogRecord;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.lang.reflect.Method;
import java.time.Instant;
import java.util.Set;

/**
 * 功能描述: <br>
 * <p>
 *  <日志处理器>
 * </p>
 * @author xuliangliang
 * @since 2025/2/23 17:34
 * @version 1.0.0
 */
public interface LogHandler {

    /**
     * 开始日志记录
     *
     * @param startTime 开始时间
     * @param request   请求对象
     * @return 日志记录器
     */
    LogRecord.Started start(Instant startTime, HttpServletRequest request);

    /**
     * 结束日志记录
     *
     * @param started  开始日志记录器
     * @param endTime  结束时间
     * @param response 响应对象
     * @param includes 包含信息
     * @return 日志记录
     */
    LogRecord finish(LogRecord.Started started, Instant endTime, HttpServletResponse response, Set<Include> includes);

    /**
     * 结束日志记录
     *
     * @param started      开始日志记录器-
     * @param endTime      结束时间
     * @param response     响应对象
     * @param includes     包含信息
     * @param targetMethod 目标方法
     * @param targetClass  目标类
     * @return 日志记录
     */
    LogRecord finish(LogRecord.Started started,
                     Instant endTime,
                     HttpServletResponse response,
                     Set<Include> includes,
                     Method targetMethod,
                     Class<?> targetClass);

    /**
     * 记录日志描述
     *
     * @param logRecord    日志记录
     * @param targetMethod 目标方法
     */
    void logDescription(LogRecord logRecord, Method targetMethod);

    /**
     * 记录所属模块
     *
     * @param logRecord    日志记录
     * @param targetMethod 目标方法
     * @param targetClass  目标类
     */
    void logModule(LogRecord logRecord, Method targetMethod, Class<?> targetClass);

    /**
     * 获取日志包含信息
     *
     * @param includes     默认包含信息
     * @param targetMethod 目标方法
     * @param targetClass  目标类
     * @return 日志包含信息
     */
    Set<Include> getIncludes(Set<Include> includes, Method targetMethod, Class<?> targetClass);
}
