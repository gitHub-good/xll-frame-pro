package com.xll.frame.starter.system.domain.system.impl;

import com.xll.frame.starter.core.validation.CheckUtils;
import com.xll.frame.starter.extension.crud.core.model.resp.LabelValueResp;
import com.xll.frame.starter.extension.crud.mp.service.BaseServiceImpl;
import com.xll.frame.starter.system.domain.system.DictItemService;
import com.xll.frame.starter.system.domain.system.DictService;
import com.xll.frame.starter.system.infrastructure.model.entity.DictDO;
import com.xll.frame.starter.system.infrastructure.model.query.DictQuery;
import com.xll.frame.starter.system.infrastructure.model.req.DictReq;
import com.xll.frame.starter.system.infrastructure.model.resp.DictResp;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.xll.frame.starter.system.infrastructure.mapper.DictMapper;

import java.util.List;
import java.util.Optional;

/**
 * 功能描述: <br>
 * <p>
 *  <字典业务实现>
 * </p>
 * @author xuliangliang
 * @since 2025/3/23 00:54
 * @version 1.0.0
 */
@Service
@RequiredArgsConstructor
public class DictServiceImpl extends BaseServiceImpl<DictMapper, DictDO, DictResp, DictResp, DictQuery, DictReq> implements DictService {

    private final DictItemService dictItemService;

    @Override
    public void beforeAdd(DictReq req) {
        String name = req.getName();
        CheckUtils.throwIf(this.isNameExists(name, null), "新增失败，[{}] 已存在", name);
        String code = req.getCode();
        CheckUtils.throwIf(this.isCodeExists(code, null), "新增失败，[{}] 已存在", code);
    }

    @Override
    public void beforeUpdate(DictReq req, Long id) {
        String name = req.getName();
        CheckUtils.throwIf(this.isNameExists(name, id), "修改失败，[{}] 已存在", name);
        DictDO oldDict = super.getById(id);
        CheckUtils.throwIfNotEqual(req.getCode(), oldDict.getCode(), "不允许修改字典编码");
    }

    @Override
    public void beforeDelete(List<Long> ids) {
        List<DictDO> list = baseMapper.lambdaQuery()
            .select(DictDO::getName, DictDO::getIsSystem)
            .in(DictDO::getId, ids)
            .list();
        Optional<DictDO> isSystemData = list.stream().filter(DictDO::getIsSystem).findFirst();
        CheckUtils.throwIf(isSystemData::isPresent, "所选字典 [{}] 是系统内置字典，不允许删除", isSystemData.orElseGet(DictDO::new)
            .getName());
        dictItemService.deleteByDictIds(ids);
    }

    @Override
    public List<LabelValueResp> listEnumDict() {
        List<String> enumDictNameList = dictItemService.listEnumDictNames();
        return enumDictNameList.stream().map(name -> new LabelValueResp(name, name)).toList();
    }

    /**
     * 名称是否存在
     *
     * @param name 名称
     * @param id   ID
     * @return 是否存在
     */
    private boolean isNameExists(String name, Long id) {
        return baseMapper.lambdaQuery().eq(DictDO::getName, name).ne(null != id, DictDO::getId, id).exists();
    }

    /**
     * 编码是否存在
     *
     * @param code 编码
     * @param id   ID
     * @return 是否存在
     */
    private boolean isCodeExists(String code, Long id) {
        return baseMapper.lambdaQuery().eq(DictDO::getCode, code).ne(null != id, DictDO::getId, id).exists();
    }
}