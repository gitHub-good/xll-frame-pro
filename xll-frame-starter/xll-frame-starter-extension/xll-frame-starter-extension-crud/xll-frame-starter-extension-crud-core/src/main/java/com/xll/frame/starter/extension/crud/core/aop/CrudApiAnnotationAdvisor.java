package com.xll.frame.starter.extension.crud.core.aop;

import org.aopalliance.aop.Advice;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.AbstractPointcutAdvisor;
import org.springframework.aop.support.ComposablePointcut;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;

import java.lang.annotation.Annotation;

/**
 * 功能描述: <br>
 * <p>
 *  <CRUD API 注解通知>
 * </p>
 * @author xuliangliang
 * @since 2025/2/16 20:04
 * @version 1.0.0
 */
public class CrudApiAnnotationAdvisor extends AbstractPointcutAdvisor implements BeanFactoryAware {

    private final Advice advice;
    private final Pointcut pointcut;

    public CrudApiAnnotationAdvisor(CrudApiAnnotationInterceptor advice, Class<? extends Annotation> annotation) {
        this.advice = advice;
        this.pointcut = new ComposablePointcut(AnnotationMatchingPointcut.forMethodAnnotation(annotation));
    }

    @Override
    public Pointcut getPointcut() {
        return this.pointcut;
    }

    @Override
    public Advice getAdvice() {
        return this.advice;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        if (this.advice instanceof BeanFactoryAware beanFactoryAware) {
            beanFactoryAware.setBeanFactory(beanFactory);
        }
    }
}
