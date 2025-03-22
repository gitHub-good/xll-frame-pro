package com.xll.frame.starter.common.context;

import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.servlet.JakartaServletUtil;
import com.xll.frame.starter.core.util.ExceptionUtils;
import com.xll.frame.starter.core.util.IpUtils;
import com.xll.frame.starter.web.util.ServletUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 功能描述: <br>
 * <p>
 *  <用户额外上下文>
 * </p>
 * @author xuliangliang
 * @since 2025/2/23 16:47
 * @version 1.0.0
 */
@Data
@NoArgsConstructor
public class UserExtraContext implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * IP
     */
    private String ip;

    /**
     * IP 归属地
     */
    private String address;

    /**
     * 浏览器
     */
    private String browser;

    /**
     * 操作系统
     */
    private String os;

    /**
     * 登录时间
     */
    private LocalDateTime loginTime;

    public UserExtraContext(HttpServletRequest request) {
        this.ip = JakartaServletUtil.getClientIP(request);
        this.address = ExceptionUtils.exToNull(() -> IpUtils.getIpv4Address(this.ip));
        this.setBrowser(ServletUtils.getBrowser(request));
        this.setLoginTime(LocalDateTime.now());
        this.setOs(StrUtil.subBefore(ServletUtils.getOs(request), " or", false));
    }
}
