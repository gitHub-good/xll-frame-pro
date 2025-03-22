package com.xll.frame.system.domain.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.StrUtil;
import com.alicp.jetcache.anno.Cached;
import com.xll.frame.starter.cache.redisson.util.RedisUtils;
import com.xll.frame.starter.common.constant.CacheConstants;
import com.xll.frame.starter.core.autoconfigure.project.ProjectProperties;
import com.xll.frame.starter.core.constant.StringConstants;
import com.xll.frame.starter.core.enums.BaseEnum;
import com.xll.frame.starter.core.validation.CheckUtils;
import com.xll.frame.starter.extension.crud.core.model.resp.LabelValueResp;
import com.xll.frame.starter.extension.crud.mp.service.BaseServiceImpl;
import com.xll.frame.system.domain.DictItemService;
import com.xll.frame.system.infrastructure.model.entity.DictItemDO;
import com.xll.frame.system.infrastructure.model.query.DictItemQuery;
import com.xll.frame.system.infrastructure.model.req.DictItemReq;
import com.xll.frame.system.infrastructure.model.resp.DictItemResp;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.xll.frame.system.infrastructure.mapper.DictItemMapper;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * 功能描述: <br>
 * <p>
 *  <字典项业务实现>
 * </p>
 * @author xuliangliang
 * @since 2025/3/23 01:55
 * @version 1.0.0
 */
@Service
@RequiredArgsConstructor
public class DictItemServiceImpl extends BaseServiceImpl<DictItemMapper, DictItemDO, DictItemResp, DictItemResp, DictItemQuery, DictItemReq> implements DictItemService {

    private final ProjectProperties projectProperties;
    private static final Map<String, List<LabelValueResp>> ENUM_DICT_CACHE = new ConcurrentHashMap<>();

    @Override
    public void beforeAdd(DictItemReq req) {
        String value = req.getValue();
        CheckUtils.throwIf(this.isValueExists(value, null, req.getDictId()), "新增失败，字典值 [{}] 已存在", value);
        RedisUtils.deleteByPattern(CacheConstants.DICT_KEY_PREFIX + StringConstants.ASTERISK);
    }

    @Override
    public void beforeUpdate(DictItemReq req, Long id) {
        String value = req.getValue();
        CheckUtils.throwIf(this.isValueExists(value, id, req.getDictId()), "修改失败，字典值 [{}] 已存在", value);
        RedisUtils.deleteByPattern(CacheConstants.DICT_KEY_PREFIX + StringConstants.ASTERISK);
    }

    @Override
    @Cached(key = "#dictCode", name = CacheConstants.DICT_KEY_PREFIX)
    public List<LabelValueResp> listByDictCode(String dictCode) {
        return Optional.ofNullable(ENUM_DICT_CACHE.get(dictCode.toLowerCase()))
            .orElseGet(() -> baseMapper.listByDictCode(dictCode));
    }

    @Override
    public void deleteByDictIds(List<Long> dictIds) {
        if (CollUtil.isEmpty(dictIds)) {
            return;
        }
        baseMapper.lambdaUpdate().in(DictItemDO::getDictId, dictIds).remove();
        RedisUtils.deleteByPattern(CacheConstants.DICT_KEY_PREFIX + StringConstants.ASTERISK);
    }

    @Override
    public List<String> listEnumDictNames() {
        return ENUM_DICT_CACHE.keySet().stream().toList();
    }

    /**
     * 字典值是否存在
     *
     * @param value  字典值
     * @param id     ID
     * @param dictId 字典 ID
     * @return 是否存在
     */
    private boolean isValueExists(String value, Long id, Long dictId) {
        return baseMapper.lambdaQuery()
            .eq(DictItemDO::getValue, value)
            .eq(DictItemDO::getDictId, dictId)
            .ne(null != id, DictItemDO::getId, id)
            .exists();
    }

    /**
     * 将枚举转换为枚举字典
     *
     * @param enumClass 枚举类型
     * @return 枚举字典
     */
    private List<LabelValueResp> toEnumDict(Class<?> enumClass) {
        Object[] enumConstants = enumClass.getEnumConstants();
        return Arrays.stream(enumConstants).map(e -> {
            BaseEnum baseEnum = (BaseEnum)e;
            return new LabelValueResp(baseEnum.getDescription(), baseEnum.getValue(), baseEnum.getColor());
        }).toList();
    }

    /**
     * 缓存枚举字典
     */
    @PostConstruct
    public void init() {
        Set<Class<?>> classSet = ClassUtil.scanPackageBySuper(projectProperties.getBasePackage(), BaseEnum.class);
        ENUM_DICT_CACHE.putAll(classSet.stream()
            .collect(Collectors.toMap(cls -> StrUtil.toUnderlineCase(cls.getSimpleName())
                .toLowerCase(), this::toEnumDict)));
    }
}