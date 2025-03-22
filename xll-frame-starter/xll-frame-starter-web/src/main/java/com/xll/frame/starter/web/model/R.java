package com.xll.frame.starter.web.model;

import cn.hutool.extra.spring.SpringUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.feiniaojin.gracefulresponse.data.Response;
import com.feiniaojin.gracefulresponse.data.ResponseStatus;
import com.xll.frame.starter.web.autoconfigure.response.GlobalResponseProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 功能描述: <br>
 * <p>
 *  <响应信息>
 * </p>
 * @author xuliangliang
 * @since 2025/2/16 18:59
 * @version 1.0.0
 */
@Data
@Schema(description = "响应信息")
public class R<T> implements Response {

    private static final GlobalResponseProperties PROPERTIES = SpringUtil.getBean(GlobalResponseProperties.class);
    private static final String DEFAULT_SUCCESS_CODE = PROPERTIES.getDefaultSuccessCode();
    private static final String DEFAULT_SUCCESS_MSG = PROPERTIES.getDefaultSuccessMsg();
    private static final String DEFAULT_ERROR_CODE = PROPERTIES.getDefaultErrorCode();
    private static final String DEFAULT_ERROR_MSG = PROPERTIES.getDefaultErrorMsg();

    /**
     * 状态码
     */
    @Schema(description = "状态码", example = "1")
    private String code;

    /**
     * 状态信息
     */
    @Schema(description = "状态信息", example = "ok")
    private String msg;

    /**
     * 是否成功
     */
    @Schema(description = "是否成功", example = "true")
    private boolean success;

    /**
     * 时间戳
     */
    @Schema(description = "时间戳", example = "1691453288000")
    private final Long timestamp = System.currentTimeMillis();

    /**
     * 响应数据
     */
    @Schema(description = "响应数据")
    private T data;

    public R() {
    }

    public R(String code, String msg) {
        this.setCode(code);
        this.setMsg(msg);
    }

    public R(String code, String msg, T data) {
        this(code, msg);
        this.data = data;
    }

    @Override
    public void setStatus(ResponseStatus status) {
        this.setCode(status.getCode());
        this.setMsg(status.getMsg());
    }

    @Override
    @JsonIgnore
    public ResponseStatus getStatus() {
        return null;
    }

    @Override
    public void setPayload(Object payload) {
        this.data = (T)payload;
    }

    @Override
    @JsonIgnore
    public Object getPayload() {
        return null;
    }

    public void setCode(String code) {
        this.code = code;
        this.success = DEFAULT_SUCCESS_CODE.equals(code);
    }

    /**
     * 操作成功
     *
     * @return R /
     */
    public static R ok() {
        return new R(DEFAULT_SUCCESS_CODE, DEFAULT_SUCCESS_MSG);
    }

    /**
     * 操作成功
     *
     * @param data 响应数据
     * @return R /
     */
    public static R ok(Object data) {
        return new R(DEFAULT_SUCCESS_CODE, DEFAULT_SUCCESS_MSG, data);
    }

    /**
     * 操作成功
     *
     * @param msg  业务状态信息
     * @param data 响应数据
     * @return R /
     */
    public static R ok(String msg, Object data) {
        return new R(DEFAULT_SUCCESS_CODE, msg, data);
    }

    /**
     * 操作失败
     *
     * @return R /
     */
    public static R fail() {
        return new R(DEFAULT_ERROR_CODE, DEFAULT_ERROR_MSG);
    }

    /**
     * 操作失败
     *
     * @param code 业务状态码
     * @param msg  业务状态信息
     * @return R /
     */
    public static R fail(String code, String msg) {
        return new R(code, msg);
    }
}