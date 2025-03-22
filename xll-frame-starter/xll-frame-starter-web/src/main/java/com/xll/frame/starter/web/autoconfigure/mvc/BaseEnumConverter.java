package com.xll.frame.starter.web.autoconfigure.mvc;

import com.xll.frame.starter.core.enums.BaseEnum;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;

import java.util.HashMap;
import java.util.Map;

/**
 * 功能描述: <br>
 * <p>
 *  <BaseEnum 参数转换器>
 * </p>
 * @author xuliangliang
 * @since 2025/2/16 18:44
 * @version 1.0.0
 */
public class BaseEnumConverter<T extends BaseEnum<?>> implements Converter<String, T> {

    private final Map<String, T> enumMap = new HashMap<>();

    public BaseEnumConverter(Class<T> enumType) {
        T[] enums = enumType.getEnumConstants();
        for (T e : enums) {
            enumMap.put(String.valueOf(e.getValue()), e);
        }
    }

    @Override
    public T convert(@NonNull String source) {
        return enumMap.get(source);
    }
}
