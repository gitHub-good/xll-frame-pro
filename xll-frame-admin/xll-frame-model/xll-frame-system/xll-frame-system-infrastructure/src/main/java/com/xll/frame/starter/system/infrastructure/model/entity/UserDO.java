package com.xll.frame.starter.system.infrastructure.model.entity;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xll.frame.starter.common.config.mybatis.BCryptEncryptor;
import com.xll.frame.starter.common.enums.DisEnableStatusEnum;
import com.xll.frame.starter.common.enums.GenderEnum;
import com.xll.frame.starter.common.model.entity.BaseDO;
import com.xll.frame.starter.extension.crud.core.annotation.DictField;
import com.xll.frame.starter.security.crypto.annotation.FieldEncrypt;
import lombok.Data;

import java.io.Serial;
import java.time.LocalDateTime;

/**
 * 功能描述: <br>
 * <p>
 *  <用户实体>
 * </p>
 * @author xuliangliang
 * @since 2025/2/23 18:11
 * @version 1.0.0
 */
@Data
@DictField(labelKey = "nickname", extraKeys = {"username"})
@TableName("sys_user")
public class UserDO extends BaseDO {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 用户名
     */
    private String username;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 密码
     */
    @FieldEncrypt(encryptor = BCryptEncryptor.class)
    private String password;

    /**
     * 性别
     */
    private GenderEnum gender;

    /**
     * 邮箱
     */
    @FieldEncrypt
    @TableField(insertStrategy = FieldStrategy.NOT_EMPTY)
    private String email;

    /**
     * 手机号码
     */
    @FieldEncrypt
    @TableField(insertStrategy = FieldStrategy.NOT_EMPTY)
    private String phone;

    /**
     * 头像地址
     */
    private String avatar;

    /**
     * 描述
     */
    private String description;

    /**
     * 状态
     */
    private DisEnableStatusEnum status;

    /**
     * 是否为系统内置数据
     */
    private Boolean isSystem;

    /**
     * 最后一次修改密码时间
     */
    private LocalDateTime pwdResetTime;

    /**
     * 部门 ID
     */
    private Long deptId;
}
