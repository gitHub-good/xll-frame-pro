package com.xll.frame.starter.data.core.function;

import java.io.Serializable;

/**
 * 功能描述: <br>
 * <p>
 *  <SQL 函数接口>
 * </p>
 * @author xuliangliang
 * @since 2025/2/6 21:31
 * @version 1.0.0
 */
public interface ISqlFunction {

    /**
     * find_in_set 函数
     *
     * @param value 值
     * @param set   集合
     * @return 函数实现
     */
    String findInSet(Serializable value, String set);
}
