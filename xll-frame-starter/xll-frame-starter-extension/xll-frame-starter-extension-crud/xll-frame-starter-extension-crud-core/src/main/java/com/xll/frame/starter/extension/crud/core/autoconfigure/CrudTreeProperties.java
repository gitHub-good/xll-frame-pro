package com.xll.frame.starter.extension.crud.core.autoconfigure;

import cn.hutool.core.lang.tree.TreeNodeConfig;
import com.xll.frame.starter.core.validation.CheckUtils;
import com.xll.frame.starter.extension.crud.core.annotation.TreeField;
import lombok.Data;

/**
 * 功能描述: <br>
 * <p>
 *  <CRUD 树列表配置属性>
 * </p>
 * @author xuliangliang
 * @since 2025/2/16 20:08
 * @version 1.0.0
 */
@Data
public class CrudTreeProperties {

    /**
     * ID 字段名
     */
    private String idKey = "id";

    /**
     * 父 ID 字段名
     */
    private String parentIdKey = "parentId";

    /**
     * 名称字段名
     */
    private String nameKey = "name";

    /**
     * 排序字段名
     */
    private String weightKey = "weight";

    /**
     * 子列表字段名
     */
    private String childrenKey = "children";

    /**
     * 递归深度（< 0 不限制）
     */
    private Integer deep = -1;

    /**
     * 根节点 ID
     */
    private Long rootId = 0L;

    /**
     * 生成 {@link TreeNodeConfig} 对象
     *
     * @return {@link TreeNodeConfig} 对象
     */
    public TreeNodeConfig genTreeNodeConfig() {
        return TreeNodeConfig.DEFAULT_CONFIG.setIdKey(idKey)
            .setParentIdKey(parentIdKey)
            .setNameKey(nameKey)
            .setWeightKey(weightKey)
            .setChildrenKey(childrenKey)
            .setDeep(deep < 0 ? null : deep);
    }

    /**
     * 根据 @TreeField 配置生成树结构配置
     *
     * @param treeField 树结构字段注解
     * @return 树结构配置
     */
    public TreeNodeConfig genTreeNodeConfig(TreeField treeField) {
        CheckUtils.throwIfNull(treeField, "请添加并配置 @TreeField 树结构信息");
        return new TreeNodeConfig().setIdKey(treeField.value())
            .setParentIdKey(treeField.parentIdKey())
            .setNameKey(treeField.nameKey())
            .setWeightKey(treeField.weightKey())
            .setChildrenKey(treeField.childrenKey())
            .setDeep(treeField.deep() < 0 ? null : treeField.deep());
    }
}
