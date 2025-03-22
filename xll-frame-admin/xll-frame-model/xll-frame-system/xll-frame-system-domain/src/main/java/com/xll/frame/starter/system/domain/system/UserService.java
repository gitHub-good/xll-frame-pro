package com.xll.frame.starter.system.domain.system;

import com.xll.frame.starter.data.mp.service.IService;
import com.xll.frame.starter.extension.crud.core.service.BaseService;
import com.xll.frame.starter.system.infrastructure.model.entity.UserDO;
import com.xll.frame.starter.system.infrastructure.model.query.UserQuery;
import com.xll.frame.starter.system.infrastructure.model.req.user.*;
import com.xll.frame.starter.system.infrastructure.model.resp.user.UserDetailResp;
import com.xll.frame.starter.system.infrastructure.model.resp.user.UserImportParseResp;
import com.xll.frame.starter.system.infrastructure.model.resp.user.UserImportResp;
import com.xll.frame.starter.system.infrastructure.model.resp.user.UserResp;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * 功能描述: <br>
 * <p>
 *  <用户业务接口>
 * </p>
 * @author xuliangliang
 * @since 2025/3/23 01:18
 * @version 1.0.0
 */
public interface UserService extends BaseService<UserResp, UserDetailResp, UserQuery, UserReq>, IService<UserDO> {

    /**
     * 下载导入模板
     *
     * @param response 响应对象
     * @throws IOException /
     */
    void downloadImportTemplate(HttpServletResponse response) throws IOException;

    /**
     * 解析导入数据
     *
     * @param file 导入文件
     * @return 解析结果
     */
    UserImportParseResp parseImport(MultipartFile file);

    /**
     * 导入数据
     *
     * @param req 导入信息
     * @return 导入结果
     */
    UserImportResp importUser(UserImportReq req);

    /**
     * 重置密码
     *
     * @param req 重置信息
     * @param id  ID
     */
    void resetPassword(UserPasswordResetReq req, Long id);

    /**
     * 修改角色
     *
     * @param updateReq 修改信息
     * @param id        ID
     */
    void updateRole(UserRoleUpdateReq updateReq, Long id);

    /**
     * 上传头像
     *
     * @param avatar 头像文件
     * @param id     ID
     * @return 新头像路径
     * @throws IOException /
     */
    String updateAvatar(MultipartFile avatar, Long id) throws IOException;

    /**
     * 修改基础信息
     *
     * @param req 修改信息
     * @param id  ID
     */
    void updateBasicInfo(UserBasicInfoUpdateReq req, Long id);

    /**
     * 修改密码
     *
     * @param oldPassword 当前密码
     * @param newPassword 新密码
     * @param id          ID
     */
    void updatePassword(String oldPassword, String newPassword, Long id);

    /**
     * 修改手机号
     *
     * @param newPhone    新手机号
     * @param oldPassword 当前密码
     * @param id          ID
     */
    void updatePhone(String newPhone, String oldPassword, Long id);

    /**
     * 修改邮箱
     *
     * @param newEmail    新邮箱
     * @param oldPassword 当前密码
     * @param id          ID
     */
    void updateEmail(String newEmail, String oldPassword, Long id);

    /**
     * 根据用户名查询
     *
     * @param username 用户名
     * @return 用户信息
     */
    UserDO getByUsername(String username);

    /**
     * 根据手机号查询
     *
     * @param phone 手机号
     * @return 用户信息
     */
    UserDO getByPhone(String phone);

    /**
     * 根据邮箱查询
     *
     * @param email 邮箱
     * @return 用户信息
     */
    UserDO getByEmail(String email);

    /**
     * 根据部门 ID 列表查询
     *
     * @param deptIds 部门 ID 列表
     * @return 用户数量
     */
    Long countByDeptIds(List<Long> deptIds);
}
