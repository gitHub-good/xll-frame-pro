package com.xll.frame.starter.core.util.expression;

import java.lang.reflect.Method;
import java.util.function.Function;

/**
 * 功能描述: <br>
 * <p>
 *  <表达式解析器>
 * </p>
 * @author xuliangliang
 * @since 2025/2/6 20:57
 * @version 1.0.0
 */
public class ExpressionEvaluator implements Function<Object, Object> {

    private final Function<Object, Object> evaluator;

    public ExpressionEvaluator(String script, Method defineMethod) {
        this.evaluator = new SpelEvaluator(script, defineMethod);
    }

    @Override
    public Object apply(Object rootObject) {
        return evaluator.apply(rootObject);
    }

    Function<Object, Object> getEvaluator() {
        return evaluator;
    }
}
