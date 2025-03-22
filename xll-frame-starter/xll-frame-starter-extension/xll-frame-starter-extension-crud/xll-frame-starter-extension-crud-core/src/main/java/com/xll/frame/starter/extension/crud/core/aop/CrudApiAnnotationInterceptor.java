package com.xll.frame.starter.extension.crud.core.aop;

import com.xll.frame.starter.extension.crud.core.annotation.CrudApi;
import com.xll.frame.starter.extension.crud.core.controller.AbstractBaseController;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.support.AopUtils;
import org.springframework.core.BridgeMethodResolver;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.util.ClassUtils;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * 功能描述: <br>
 * <p>
 *  <CRUD API 注解拦截器>
 * </p>
 * @author xuliangliang
 * @since 2025/2/16 20:05
 * @version 1.0.0
 */
public class CrudApiAnnotationInterceptor implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        // 获取目标类
        Class<?> targetClass = AopUtils.getTargetClass(Objects.requireNonNull(invocation.getThis()));
        // 获取目标方法
        Method specificMethod = ClassUtils.getMostSpecificMethod(invocation.getMethod(), targetClass);
        Method targetMethod = BridgeMethodResolver.findBridgedMethod(specificMethod);
        // 获取 @CrudApi 注解
        CrudApi crudApi = AnnotatedElementUtils.findMergedAnnotation(targetMethod, CrudApi.class);
        // 执行处理
        AbstractBaseController controller = (AbstractBaseController)invocation.getThis();
        controller.preHandle(crudApi, invocation.getArguments(), targetMethod, targetClass);
        return invocation.proceed();
    }
}
