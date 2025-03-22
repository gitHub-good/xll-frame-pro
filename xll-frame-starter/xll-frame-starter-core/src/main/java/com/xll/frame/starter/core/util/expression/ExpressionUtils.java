package com.xll.frame.starter.core.util.expression;

import cn.hutool.core.text.CharSequenceUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

/**
 * 功能描述: <br>
 * <p>
 *  <表达式解析工具类>
 * </p>
 * @author xuliangliang
 * @since 2025/2/6 20:58
 * @version 1.0.0
 */
@Slf4j
public class ExpressionUtils {

    private ExpressionUtils() {
    }

    /**
     * 解析
     *
     * @param script 表达式
     * @param target 目标对象
     * @param method 目标方法
     * @param args   方法参数
     * @return 解析结果
     */
    public static Object eval(String script, Object target, Method method, Object... args) {
        try {
            if (CharSequenceUtil.isBlank(script)) {
                return null;
            }
            ExpressionEvaluator expressionEvaluator = new ExpressionEvaluator(script, method);
            ExpressionInvokeContext invokeContext = new ExpressionInvokeContext(method, args, target);
            return expressionEvaluator.apply(invokeContext);
        } catch (Exception e) {
            log.error("Error occurs when eval script \"{}\" in {} : {}", script, method, e.getMessage(), e);
            return null;
        }
    }
}
