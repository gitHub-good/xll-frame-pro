package com.xll.frame.starter.system.infrastructure.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.xll.frame.starter.common.enums.DisEnableStatusEnum;
import com.xll.frame.starter.common.model.entity.BaseDO;
import com.xll.frame.starter.system.infrastructure.enums.MenuTypeEnum;
import lombok.Data;

import java.io.Serial;

/**
 * 功能描述: <br>
 * <p>
 *  <菜单实体>
 * </p>
 * @author xuliangliang
 * @since 2025/3/23 00:32
 * @version 1.0.0
 */
@Data
@TableName("sys_menu")
public class MenuDO extends BaseDO {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 标题
     */
    private String title;

    /**
     * 上级菜单 ID
     */
    private Long parentId;

    /**
     * 类型
     */
    private MenuTypeEnum type;

    /**
     * 路由地址
     */
    private String path;

    /**
     * 组件名称
     */
    private String name;

    /**
     * 组件路径
     */
    private String component;

    /**
     * 重定向地址
     */
    private String redirect;

    /**
     * 图标
     */
    private String icon;

    /**
     * 是否外链
     */
    private Boolean isExternal;

    /**
     * 是否缓存
     */
    private Boolean isCache;

    /**
     * 是否隐藏
     */
    private Boolean isHidden;

    /**
     * 权限标识
     */
    private String permission;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 状态
     */
    private DisEnableStatusEnum status;
}
