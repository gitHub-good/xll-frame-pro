package com.xll.frame.starter.data.mp.service.impl;

import cn.hutool.core.util.ClassUtil;
import com.xll.frame.starter.core.util.ReflectUtils;
import com.xll.frame.starter.core.validation.CheckUtils;
import com.xll.frame.starter.data.mp.base.BaseMapper;
import com.xll.frame.starter.data.mp.service.IService;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.List;

/**
 * 功能描述: <br>
 * <p>
 *  <通用业务实现类>
 * </p>
 * @author xuliangliang
 * @since 2025/2/6 21:41
 * @version 1.0.0
 */
public class ServiceImpl<M extends BaseMapper<T>, T> extends com.baomidou.mybatisplus.extension.service.impl.ServiceImpl<M, T> implements IService<T> {

    private List<Field> entityFields;

    @Override
    public T getById(Serializable id) {
        return this.getById(id, true);
    }

    /**
     * 获取当前实体类型字段
     *
     * @return 当前实体类型字段列表
     */
    public List<Field> getEntityFields() {
        if (this.entityFields == null) {
            this.entityFields = ReflectUtils.getNonStaticFields(this.getEntityClass());
        }
        return this.entityFields;
    }

    /**
     * 根据 ID 查询
     *
     * @param id            ID
     * @param isCheckExists 是否检查存在
     * @return 实体信息
     */
    protected T getById(Serializable id, boolean isCheckExists) {
        T entity = baseMapper.selectById(id);
        if (isCheckExists) {
            CheckUtils.throwIfNotExists(entity, ClassUtil.getClassName(this.getEntityClass(), true), "ID", id);
        }
        return entity;
    }
}