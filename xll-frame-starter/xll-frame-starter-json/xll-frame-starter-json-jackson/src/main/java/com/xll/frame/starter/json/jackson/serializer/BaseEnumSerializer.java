package com.xll.frame.starter.json.jackson.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.xll.frame.starter.core.enums.BaseEnum;

import java.io.IOException;

/**
 * 功能描述: <br>
 * <p>
 *  <枚举接口 BaseEnum 序列化器>
 * </p>
 * @author xuliangliang
 * @since 2025/2/16 18:35
 * @version 1.0.0
 */
@JacksonStdImpl
public class BaseEnumSerializer extends JsonSerializer<BaseEnum> {

    /**
     * 静态实例
     */
    public static final BaseEnumSerializer SERIALIZER_INSTANCE = new BaseEnumSerializer();

    @Override
    public void serialize(BaseEnum value, JsonGenerator generator, SerializerProvider serializers) throws IOException {
        generator.writeObject(value.getValue());
    }
}
