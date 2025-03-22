package com.xll.frame.starter.common.context;

import com.xll.frame.starter.common.enums.DataScopeEnum;
import lombok.Data;
import java.io.Serial;
import java.io.Serializable;

/**
 * 功能描述: <br>
 * <p>
 *  <角色上下文>
 * </p>
 * @author xuliangliang
 * @since 2025/2/23 16:36
 * @version 1.0.0
 */
@Data
public class RoleContext implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private Long id;

    /**
     * 角色编码
     */
    private String code;

    /**
     * 数据权限
     */
    private DataScopeEnum dataScope;
}
