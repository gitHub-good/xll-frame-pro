package com.xll.frame.starter.common.controller;

import cn.dev33.satoken.annotation.SaIgnore;
import cn.dev33.satoken.context.SaHolder;
import cn.dev33.satoken.context.model.SaRequest;
import cn.dev33.satoken.sign.SaSignTemplate;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.annotation.AnnotationUtil;
import cn.hutool.core.text.CharSequenceUtil;
import com.xll.frame.starter.core.constant.StringConstants;
import com.xll.frame.starter.extension.crud.core.annotation.CrudApi;
import com.xll.frame.starter.extension.crud.core.annotation.CrudRequestMapping;
import com.xll.frame.starter.extension.crud.core.controller.AbstractBaseController;
import com.xll.frame.starter.extension.crud.core.enums.Api;
import com.xll.frame.starter.extension.crud.core.service.BaseService;

import java.lang.reflect.Method;
import java.util.List;

/**
 * 控制器基类
 *
 * @param <S> 业务接口
 * @param <L> 列表类型
 * @param <D> 详情类型
 * @param <Q> 查询条件
 * @param <C> 创建或修改参数类型
 * @author xuliangliang
 */
public class BaseController<S extends BaseService<L, D, Q, C>, L, D, Q, C> extends AbstractBaseController<S, L, D, Q, C> {

    @Override
    public void preHandle(CrudApi crudApi, Object[] args, Method targetMethod, Class<?> targetClass) throws Exception {
        SaRequest saRequest = SaHolder.getRequest();
        List<String> paramNames = saRequest.getParamNames();
        if (paramNames.stream().anyMatch(SaSignTemplate.sign::equals)) {
            return;
        }
        if (AnnotationUtil.hasAnnotation(targetMethod, SaIgnore.class) || AnnotationUtil
            .hasAnnotation(targetClass, SaIgnore.class)) {
            return;
        }
        CrudRequestMapping crudRequestMapping = targetClass.getDeclaredAnnotation(CrudRequestMapping.class);
        String path = crudRequestMapping.value();
        String prefix = String.join(StringConstants.COLON, CharSequenceUtil.splitTrim(path, StringConstants.SLASH));
        Api api = crudApi.value();
        String apiName = Api.PAGE.equals(api) || Api.TREE.equals(api) ? Api.LIST.name() : api.name();
        StpUtil.checkPermission("%s:%s".formatted(prefix, apiName.toLowerCase()));
    }
}
