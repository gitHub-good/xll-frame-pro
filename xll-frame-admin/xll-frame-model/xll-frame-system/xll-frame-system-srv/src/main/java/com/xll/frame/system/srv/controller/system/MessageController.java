package com.xll.frame.system.srv.controller.system;

import com.xll.frame.starter.common.context.UserContextHolder;
import com.xll.frame.starter.extension.crud.core.model.query.PageQuery;
import com.xll.frame.starter.extension.crud.mp.model.resp.PageResp;
import com.xll.frame.starter.log.core.annotation.Log;
import com.xll.frame.system.infrastructure.service.MessageService;
import com.xll.frame.system.infrastructure.service.MessageUserService;
import com.xll.frame.system.infrastructure.model.query.MessageQuery;
import com.xll.frame.system.infrastructure.model.resp.MessageResp;
import com.xll.frame.system.infrastructure.model.resp.MessageUnreadResp;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 功能描述: <br>
 * <p>
 *  <消息管理 API>
 * </p>
 * @author xuliangliang
 * @since 2025/3/23 01:52
 * @version 1.0.0
 */
@Tag(name = "消息管理 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/system/message")
public class MessageController {

    private final MessageService baseService;
    private final MessageUserService messageUserService;

    @Operation(summary = "分页查询列表", description = "分页查询列表")
    @GetMapping
    public PageResp<MessageResp> page(MessageQuery query, @Validated PageQuery pageQuery) {
        query.setUserId(UserContextHolder.getUserId());
        return baseService.page(query, pageQuery);
    }

    @Operation(summary = "删除数据", description = "删除数据")
    @Parameter(name = "ids", description = "ID 列表", example = "1,2", in = ParameterIn.PATH)
    @DeleteMapping("/{ids}")
    public void delete(@PathVariable List<Long> ids) {
        baseService.delete(ids);
    }

    @Operation(summary = "标记已读", description = "将消息标记为已读状态")
    @Parameter(name = "ids", description = "消息ID列表", example = "1,2", in = ParameterIn.QUERY)
    @PatchMapping("/read")
    public void readMessage(@RequestParam(required = false) List<Long> ids) {
        messageUserService.readMessage(ids);
    }

    @Log(ignore = true)
    @Operation(summary = "查询未读消息数量", description = "查询当前用户的未读消息数量")
    @Parameter(name = "isDetail", description = "是否查询详情", example = "true", in = ParameterIn.QUERY)
    @GetMapping("/unread")
    public MessageUnreadResp countUnreadMessage(@RequestParam(required = false) Boolean detail) {
        return messageUserService.countUnreadMessageByUserId(UserContextHolder.getUserId(), detail);
    }
}