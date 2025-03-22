package com.xll.frame.starter.web.autoconfigure.response;

import cn.hutool.core.util.ClassUtil;
import com.xll.frame.starter.api.doc.util.DocUtils;
import org.apache.commons.lang3.reflect.TypeUtils;
import org.springdoc.core.parsers.ReturnTypeParser;
import org.springframework.core.MethodParameter;

import java.lang.reflect.Type;

/**
 * SpringDoc 全局响应处理器
 * <p>
 * 接口文档全局添加响应格式 {@link com.xll.frame.starter.web.model.R}
 * </p>
 */
public class ApiDocGlobalResponseHandler implements ReturnTypeParser {

    private final GlobalResponseProperties globalResponseProperties;
    private final Class<Object> responseClass;

    public ApiDocGlobalResponseHandler(GlobalResponseProperties globalResponseProperties) {
        this.globalResponseProperties = globalResponseProperties;
        this.responseClass = ClassUtil.loadClass(globalResponseProperties.getResponseClassFullName());
    }

    /**
     * 获取返回类型
     *
     * @param methodParameter 方法参数
     * @return {@link Type }
     */
    @Override
    public Type getReturnType(MethodParameter methodParameter) {
        // 获取返回类型
        Type returnType = ReturnTypeParser.super.getReturnType(methodParameter);
        // 判断是否具有 RestController 注解
        if (!DocUtils.hasRestControllerAnnotation(methodParameter.getContainingClass())) {
            return returnType;
        }
        // 如果为响应类型，则直接返回
        if (returnType.getTypeName().contains(globalResponseProperties.getResponseClassFullName())) {
            return returnType;
        }
        // 如果是 void类型，则返回 R<Void>
        if (returnType == void.class || returnType == Void.class) {
            return TypeUtils.parameterize(responseClass, Void.class);
        }
        // 返回 R<T>
        return TypeUtils.parameterize(responseClass, returnType);
    }
}
