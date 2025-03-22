package com.xll.frame.starter.extension.crud.core.handler;

import com.xll.frame.starter.extension.crud.core.annotation.CrudApi;

import java.lang.reflect.Method;

/**
 * 功能描述: <br>
 * <p>
 *  <CRUD API 处理器>
 * </p>
 * @author xuliangliang
 * @since 2025/2/16 20:08
 * @version 1.0.0
 */
public interface CrudApiHandler {

    /**
     * 前置处理
     *
     * @param crudApi      CRUD API 注解
     * @param args         方法参数
     * @param targetMethod 目标方法
     * @param targetClass  目标类
     * @throws Exception 处理异常
     */
    void preHandle(CrudApi crudApi, Object[] args, Method targetMethod, Class<?> targetClass) throws Exception;
}
