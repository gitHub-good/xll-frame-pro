package com.xll.frame.starter.log.core;

import cn.hutool.core.annotation.AnnotationUtil;
import cn.hutool.core.text.CharSequenceUtil;
import com.xll.frame.starter.log.core.annotation.Log;
import com.xll.frame.starter.log.core.enums.Include;
import com.xll.frame.starter.log.core.http.servlet.RecordableServletHttpRequest;
import com.xll.frame.starter.log.core.http.servlet.RecordableServletHttpResponse;
import com.xll.frame.starter.log.core.model.LogRecord;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.lang.reflect.Method;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

/**
 * 功能描述: <br>
 * <p>
 *  <日志处理器基类>
 * </p>
 * @author xuliangliang
 * @since 2025/2/23 17:33
 * @version 1.0.0
 */
public abstract class AbstractLogHandler implements LogHandler {

    @Override
    public LogRecord.Started start(Instant startTime, HttpServletRequest request) {
        return LogRecord.start(startTime, new RecordableServletHttpRequest(request));
    }

    @Override
    public LogRecord finish(LogRecord.Started started,
                            Instant endTime,
                            HttpServletResponse response,
                            Set<Include> includes,
                            Method targetMethod,
                            Class<?> targetClass) {
        Set<Include> includeSet = this.getIncludes(includes, targetMethod, targetClass);
        LogRecord logRecord = this.finish(started, endTime, response, includeSet);
        // 记录日志描述
        if (includeSet.contains(Include.DESCRIPTION)) {
            this.logDescription(logRecord, targetMethod);
        }
        // 记录所属模块
        if (includeSet.contains(Include.MODULE)) {
            this.logModule(logRecord, targetMethod, targetClass);
        }
        return logRecord;
    }

    @Override
    public LogRecord finish(LogRecord.Started started,
                            Instant endTime,
                            HttpServletResponse response,
                            Set<Include> includes) {
        return started.finish(endTime, new RecordableServletHttpResponse(response, response.getStatus()), includes);
    }

    /**
     * 记录日志描述
     *
     * @param logRecord    日志记录
     * @param targetMethod 目标方法
     */
    @Override
    public void logDescription(LogRecord logRecord, Method targetMethod) {
        logRecord.setDescription("请在该接口方法上添加 @Log(value) 来指定日志描述");
        Log methodLog = AnnotationUtil.getAnnotation(targetMethod, Log.class);
        // 例如：@Log("新增部门") -> 新增部门
        if (null != methodLog && CharSequenceUtil.isNotBlank(methodLog.value())) {
            logRecord.setDescription(methodLog.value());
            return;
        }
        // 例如：@Operation(summary="新增部门") -> 新增部门
        Operation methodOperation = AnnotationUtil.getAnnotation(targetMethod, Operation.class);
        if (null != methodOperation && CharSequenceUtil.isNotBlank(methodOperation.summary())) {
            logRecord.setDescription(methodOperation.summary());
        }
    }

    /**
     * 记录所属模块
     *
     * @param logRecord    日志记录
     * @param targetMethod 目标方法
     * @param targetClass  目标类
     */
    @Override
    public void logModule(LogRecord logRecord, Method targetMethod, Class<?> targetClass) {
        logRecord.setModule("请在该接口方法或类上添加 @Log(module) 来指定所属模块");
        Log methodLog = AnnotationUtil.getAnnotation(targetMethod, Log.class);
        // 例如：@Log(module = "部门管理") -> 部门管理
        // 方法级注解优先级高于类级注解
        if (null != methodLog && CharSequenceUtil.isNotBlank(methodLog.module())) {
            logRecord.setModule(methodLog.module());
            return;
        }
        Log classLog = AnnotationUtil.getAnnotation(targetClass, Log.class);
        if (null != classLog && CharSequenceUtil.isNotBlank(classLog.module())) {
            logRecord.setModule(classLog.module());
            return;
        }
        // 例如：@Tag(name = "部门管理") -> 部门管理
        Tag classTag = AnnotationUtil.getAnnotation(targetClass, Tag.class);
        if (null != classTag && CharSequenceUtil.isNotBlank(classTag.name())) {
            logRecord.setModule(classTag.name());
        }
    }

    @Override
    public Set<Include> getIncludes(Set<Include> includes, Method targetMethod, Class<?> targetClass) {
        Log classLog = AnnotationUtil.getAnnotation(targetClass, Log.class);
        Set<Include> includeSet = new HashSet<>(includes);
        if (null != classLog) {
            this.processInclude(includeSet, classLog);
        }
        // 方法级注解优先级高于类级注解
        Log methodLog = AnnotationUtil.getAnnotation(targetMethod, Log.class);
        if (null != methodLog) {
            this.processInclude(includeSet, methodLog);
        }
        return includeSet;
    }

    /**
     * 处理日志包含信息
     *
     * @param includes      日志包含信息
     * @param logAnnotation Log 注解
     */
    private void processInclude(Set<Include> includes, Log logAnnotation) {
        Include[] includeArr = logAnnotation.includes();
        if (includeArr.length > 0) {
            includes.addAll(Set.of(includeArr));
        }
        Include[] excludeArr = logAnnotation.excludes();
        if (excludeArr.length > 0) {
            includes.removeAll(Set.of(excludeArr));
        }
    }
}
