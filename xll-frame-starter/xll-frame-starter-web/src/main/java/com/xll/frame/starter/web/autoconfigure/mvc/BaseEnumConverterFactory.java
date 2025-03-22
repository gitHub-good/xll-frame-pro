package com.xll.frame.starter.web.autoconfigure.mvc;

import com.xll.frame.starter.core.enums.BaseEnum;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 功能描述: <br>
 * <p>
 *  <BaseEnum 参数转换器工厂>
 * </p>
 * @author xuliangliang
 * @since 2025/2/16 18:45
 * @version 1.0.0
 */
public class BaseEnumConverterFactory implements ConverterFactory<String, BaseEnum> {

    private static final Map<Class, Converter> CONVERTER_CACHE = new ConcurrentHashMap<>();

    @Override
    public <T extends BaseEnum> Converter<String, T> getConverter(Class<T> targetType) {
        return CONVERTER_CACHE.computeIfAbsent(targetType, key -> new BaseEnumConverter<>(targetType));
    }
}
