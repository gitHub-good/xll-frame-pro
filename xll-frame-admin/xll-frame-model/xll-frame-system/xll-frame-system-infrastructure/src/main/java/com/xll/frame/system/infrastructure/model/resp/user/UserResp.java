package com.xll.frame.system.infrastructure.model.resp.user;

import cn.crane4j.annotation.Assemble;
import cn.crane4j.core.executor.handler.ManyToManyAssembleOperationHandler;
import com.xll.frame.starter.common.constant.ContainerConstants;
import com.xll.frame.starter.common.context.UserContextHolder;
import com.xll.frame.starter.common.enums.DisEnableStatusEnum;
import com.xll.frame.starter.common.enums.GenderEnum;
import com.xll.frame.starter.common.model.resp.BaseDetailResp;
import com.xll.frame.starter.security.mask.annotation.JsonMask;
import com.xll.frame.starter.security.mask.enums.MaskType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serial;
import java.util.List;
import java.util.Objects;

@Data
@Schema(description = "用户信息")
@Assemble(key = "id", prop = ":roleIds", sort = 0, container = ContainerConstants.USER_ROLE_ID_LIST)
public class UserResp extends BaseDetailResp {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 用户名
     */
    @Schema(description = "用户名", example = "zhangsan")
    private String username;

    /**
     * 昵称
     */
    @Schema(description = "昵称", example = "张三")
    private String nickname;

    /**
     * 性别
     */
    @Schema(description = "性别", example = "1")
    private GenderEnum gender;

    /**
     * 头像地址
     */
    @Schema(description = "头像地址", example = "https://himg.bdimg.com/sys/portrait/item/public.1.81ac9a9e.rf1ix17UfughLQjNo7XQ_w.jpg")
    private String avatar;

    /**
     * 邮箱
     */
    @Schema(description = "邮箱", example = "c*******@126.com")
    @JsonMask(MaskType.EMAIL)
    private String email;

    /**
     * 手机号码
     */
    @Schema(description = "手机号码", example = "188****8888")
    @JsonMask(MaskType.MOBILE_PHONE)
    private String phone;

    /**
     * 状态
     */
    @Schema(description = "状态", example = "1")
    private DisEnableStatusEnum status;

    /**
     * 是否为系统内置数据
     */
    @Schema(description = "是否为系统内置数据", example = "false")
    private Boolean isSystem;

    /**
     * 描述
     */
    @Schema(description = "描述", example = "张三描述信息")
    private String description;

    /**
     * 部门 ID
     */
    @Schema(description = "部门 ID", example = "5")
    private Long deptId;

    /**
     * 所属部门
     */
    @Schema(description = "所属部门", example = "测试部")
    private String deptName;

    /**
     * 角色 ID 列表
     */
    @Schema(description = "角色 ID 列表", example = "2")
    @Assemble(prop = ":roleNames", container = ContainerConstants.USER_ROLE_NAME_LIST, handlerType = ManyToManyAssembleOperationHandler.class)
    private List<Long> roleIds;

    /**
     * 角色名称列表
     */
    @Schema(description = "角色名称列表", example = "测试人员")
    private List<String> roleNames;

    @Override
    public Boolean getDisabled() {
        return this.getIsSystem() || Objects.equals(this.getId(), UserContextHolder.getUserId());
    }
}
