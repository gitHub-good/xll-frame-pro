package com.xll.frame.starter.core.validation.constraints;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.text.CharSequenceUtil;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.function.Function;

/**
 * 功能描述: <br>
 * <p>
 *  <枚举校验注解校验器>
 * </p>
 * @author xuliangliang
 * @since 2025/2/6 21:06
 * @version 1.0.0
 */
public class EnumValueValidator implements ConstraintValidator<EnumValue, Object> {

    private static final Logger log = LoggerFactory.getLogger(EnumValueValidator.class);
    private Class<? extends Enum> enumClass;
    private String[] enumValues;
    private String enumMethod;

    @Override
    public void initialize(EnumValue enumValue) {
        this.enumClass = enumValue.value();
        this.enumValues = enumValue.enumValues();
        this.enumMethod = enumValue.method();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        // 优先校验 enumValues
        if (enumValues.length > 0) {
            return Arrays.asList(enumValues).contains(Convert.toStr(value));
        }
        Enum[] enumConstants = enumClass.getEnumConstants();
        if (enumConstants.length == 0) {
            return false;
        }
        if (CharSequenceUtil.isBlank(enumMethod)) {
            return findEnumValue(enumConstants, Enum::toString, Convert.toStr(value));
        }
        try {
            // 枚举类指定了方法名，则调用指定方法获取枚举值
            Method method = enumClass.getMethod(enumMethod);
            for (Enum enumConstant : enumConstants) {
                if (Convert.toStr(method.invoke(enumConstant)).equals(Convert.toStr(value))) {
                    return true;
                }
            }
        } catch (Exception e) {
            log.error("An error occurred while validating the enum value, please check the @EnumValue parameter configuration.", e);
        }
        return false;
    }

    /**
     * 遍历枚举类，判断是否包含指定值
     *
     * @param enumConstants 枚举类数组
     * @param function      获取枚举值的函数
     * @param value         待校验的值
     * @return 是否包含指定值
     */
    private boolean findEnumValue(Enum[] enumConstants, Function<Enum, Object> function, Object value) {
        for (Enum enumConstant : enumConstants) {
            if (function.apply(enumConstant).equals(value)) {
                return true;
            }
        }
        return false;
    }
}
