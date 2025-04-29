package com.xll.frame.system.domain.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.alicp.jetcache.anno.Cached;
import com.xll.frame.starter.cache.redisson.util.RedisUtils;
import com.xll.frame.starter.common.constant.CacheConstants;
import com.xll.frame.starter.common.enums.DisEnableStatusEnum;
import com.xll.frame.starter.core.constant.StringConstants;
import com.xll.frame.starter.core.validation.CheckUtils;
import com.xll.frame.starter.extension.crud.mp.service.BaseServiceImpl;
import com.xll.frame.system.infrastructure.service.MenuService;
import com.xll.frame.system.infrastructure.enums.MenuTypeEnum;
import com.xll.frame.system.infrastructure.model.entity.MenuDO;
import com.xll.frame.system.infrastructure.model.query.MenuQuery;
import com.xll.frame.system.infrastructure.model.req.MenuReq;
import com.xll.frame.system.infrastructure.model.resp.MenuResp;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.xll.frame.system.infrastructure.mapper.MenuMapper;

import java.util.List;
import java.util.Set;

/**
 * 功能描述: <br>
 * <p>
 *  <菜单业务实现>
 * </p>
 * @author xuliangliang
 * @since 2025/3/23 01:01
 * @version 1.0.0
 */
@Service
@RequiredArgsConstructor
public class MenuServiceImpl extends BaseServiceImpl<MenuMapper, MenuDO, MenuResp, MenuResp, MenuQuery, MenuReq> implements MenuService {

    @Override
    public Long add(MenuReq req) {
        String title = req.getTitle();
        CheckUtils.throwIf(this.isTitleExists(title, req.getParentId(), null), "新增失败，标题 [{}] 已存在", title);
        // 目录和菜单的组件名称不能重复
        if (!MenuTypeEnum.BUTTON.equals(req.getType())) {
            String name = req.getName();
            CheckUtils.throwIf(this.isNameExists(name, null), "新增失败，组件名称 [{}] 已存在", name);
        }
        // 目录类型菜单，默认为 Layout
        if (MenuTypeEnum.DIR.equals(req.getType())) {
            req.setComponent(StrUtil.blankToDefault(req.getComponent(), "Layout"));
        }
        RedisUtils.deleteByPattern(CacheConstants.MENU_KEY_PREFIX + StringConstants.ASTERISK);
        return super.add(req);
    }

    @Override
    public void update(MenuReq req, Long id) {
        String title = req.getTitle();
        CheckUtils.throwIf(this.isTitleExists(title, req.getParentId(), id), "修改失败，标题 [{}] 已存在", title);
        // 目录和菜单的组件名称不能重复
        if (!MenuTypeEnum.BUTTON.equals(req.getType())) {
            String name = req.getName();
            CheckUtils.throwIf(this.isNameExists(name, id), "修改失败，组件名称 [{}] 已存在", name);
        }
        MenuDO oldMenu = super.getById(id);
        CheckUtils.throwIfNotEqual(req.getType(), oldMenu.getType(), "不允许修改菜单类型");
        super.update(req, id);
        RedisUtils.deleteByPattern(CacheConstants.MENU_KEY_PREFIX + StringConstants.ASTERISK);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(List<Long> ids) {
        baseMapper.lambdaUpdate().in(MenuDO::getParentId, ids).remove();
        super.delete(ids);
        RedisUtils.deleteByPattern(CacheConstants.MENU_KEY_PREFIX + StringConstants.ASTERISK);
    }

    @Override
    @Cached(key = "'ALL'", name = CacheConstants.MENU_KEY_PREFIX)
    public List<MenuResp> listAll() {
        return super.list(new MenuQuery(DisEnableStatusEnum.ENABLE), null);
    }

    @Override
    public Set<String> listPermissionByUserId(Long userId) {
        return baseMapper.selectPermissionByUserId(userId);
    }

    @Override
    @Cached(key = "#roleCode", name = CacheConstants.MENU_KEY_PREFIX)
    public List<MenuResp> listByRoleCode(String roleCode) {
        List<MenuDO> menuList = baseMapper.selectListByRoleCode(roleCode);
        List<MenuResp> list = BeanUtil.copyToList(menuList, MenuResp.class);
        list.forEach(super::fill);
        return list;
    }

    /**
     * 标题是否存在
     *
     * @param title    标题
     * @param parentId 上级 ID
     * @param id       ID
     * @return true：存在；false：不存在
     */
    private boolean isTitleExists(String title, Long parentId, Long id) {
        return baseMapper.lambdaQuery()
            .eq(MenuDO::getTitle, title)
            .eq(MenuDO::getParentId, parentId)
            .ne(null != id, MenuDO::getId, id)
            .exists();
    }

    /**
     * 名称是否存在
     *
     * @param name 标题
     * @param id   ID
     * @return true：存在；false：不存在
     */
    private boolean isNameExists(String name, Long id) {
        return baseMapper.lambdaQuery()
            .eq(MenuDO::getName, name)
            .ne(MenuDO::getType, MenuTypeEnum.BUTTON)
            .ne(null != id, MenuDO::getId, id)
            .exists();
    }
}
