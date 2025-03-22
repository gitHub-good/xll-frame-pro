package com.xll.frame.starter.core.util.expression;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Method;

/**
 * 功能描述: <br>
 * <p>
 *  <表达式上下文>
 * </p>
 * @author xuliangliang
 * @since 2025/2/6 20:57
 * @version 1.0.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExpressionInvokeContext {

    /**
     * 目标方法
     */
    private Method method;

    /**
     * 方法参数
     */
    private Object[] args;

    /**
     * 目标对象
     */
    private Object target;
}
