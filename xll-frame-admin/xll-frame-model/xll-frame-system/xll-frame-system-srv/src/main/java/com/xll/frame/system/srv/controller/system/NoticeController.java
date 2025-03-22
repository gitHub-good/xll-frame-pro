package com.xll.frame.system.srv.controller.system;

import com.xll.frame.starter.common.controller.BaseController;
import com.xll.frame.starter.core.validation.ValidationUtils;
import com.xll.frame.starter.extension.crud.core.annotation.CrudApi;
import com.xll.frame.starter.extension.crud.core.annotation.CrudRequestMapping;
import com.xll.frame.starter.extension.crud.core.enums.Api;
import com.xll.frame.system.domain.NoticeService;
import com.xll.frame.system.infrastructure.enums.NoticeScopeEnum;
import com.xll.frame.system.infrastructure.model.query.NoticeQuery;
import com.xll.frame.system.infrastructure.model.req.NoticeReq;
import com.xll.frame.system.infrastructure.model.resp.NoticeDetailResp;
import com.xll.frame.system.infrastructure.model.resp.NoticeResp;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Method;
import java.time.LocalDateTime;

/**
 * 功能描述: <br>
 * <p>
 *  <公告管理 API>
 * </p>
 * @author xuliangliang
 * @since 2025/3/23 01:52
 * @version 1.0.0
 */
@Tag(name = "公告管理 API")
@RestController
@CrudRequestMapping(value = "/system/notice", api = {Api.PAGE, Api.DETAIL, Api.ADD, Api.UPDATE, Api.DELETE})
public class NoticeController extends BaseController<NoticeService, NoticeResp, NoticeDetailResp, NoticeQuery, NoticeReq> {

    @Override
    public void preHandle(CrudApi crudApi, Object[] args, Method targetMethod, Class<?> targetClass) throws Exception {
        super.preHandle(crudApi, args, targetMethod, targetClass);
        Api api = crudApi.value();
        if (!(Api.ADD.equals(api) || Api.UPDATE.equals(api))) {
            return;
        }
        NoticeReq req = (NoticeReq)args[0];
        // 校验生效时间
        LocalDateTime effectiveTime = req.getEffectiveTime();
        LocalDateTime terminateTime = req.getTerminateTime();
        if (null != effectiveTime && null != terminateTime) {
            ValidationUtils.throwIf(terminateTime.isBefore(effectiveTime), "终止时间必须晚于生效时间");
        }
        // 校验通知范围
        if (NoticeScopeEnum.USER.equals(req.getNoticeScope())) {
            ValidationUtils.throwIfEmpty(req.getNoticeUsers(), "通知用户不能为空");
        }
    }
}