package com.xll.frame.starter.log.aop.aspect;

import com.xll.frame.starter.log.core.model.LogProperties;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.Duration;
import java.time.Instant;

/**
 * 功能描述: <br>
 * <p>
 *  <访问日志切面>
 * </p>
 * @author xuliangliang
 * @since 2025/2/23 17:38
 * @version 1.0.0
 */
@Slf4j
@Aspect
public class AccessLogAspect {

    private final LogProperties logProperties;

    public AccessLogAspect(LogProperties logProperties) {
        this.logProperties = logProperties;
    }

    /**
     * 切点 - 匹配所有控制器层的 GET 请求方法
     */
    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void pointcut() {
    }

    /**
     * 切点 - 匹配所有控制器层的 GET 请求方法
     */
    @Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping)")
    public void pointcutGet() {
    }

    /**
     * 切点 - 匹配所有控制器层的 POST 请求方法
     */
    @Pointcut("@annotation(org.springframework.web.bind.annotation.PostMapping)")
    public void pointcutPost() {
    }

    /**
     * 切点 - 匹配所有控制器层的 PUT 请求方法
     */
    @Pointcut("@annotation(org.springframework.web.bind.annotation.PutMapping)")
    public void pointcutPut() {
    }

    /**
     * 切点 - 匹配所有控制器层的 DELETE 请求方法
     */
    @Pointcut("@annotation(org.springframework.web.bind.annotation.DeleteMapping)")
    public void pointcutDelete() {
    }

    /**
     * 切点 - 匹配所有控制器层的 PATCH 请求方法
     */
    @Pointcut("@annotation(org.springframework.web.bind.annotation.PatchMapping)")
    public void pointcutPatch() {
    }

    /**
     * 打印访问日志
     *
     * @param joinPoint 切点
     * @return 返回结果
     * @throws Throwable 异常
     */
    @Around("pointcut() || pointcutGet() || pointcutPost() || pointcutPut() || pointcutDelete() || pointcutPatch()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        Instant startTime = Instant.now();
        // 非 Web 环境不记录
        ServletRequestAttributes attributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        if (attributes == null) {
            return joinPoint.proceed();
        }
        HttpServletRequest request = attributes.getRequest();
        HttpServletResponse response = attributes.getResponse();
        try {
            // 打印请求日志
            if (Boolean.TRUE.equals(logProperties.getIsPrint())) {
                log.info("[{}] {}", request.getMethod(), request.getRequestURI());
            }
            return joinPoint.proceed();
        } finally {
            Instant endTime = Instant.now();
            if (Boolean.TRUE.equals(logProperties.getIsPrint())) {
                Duration timeTaken = Duration.between(startTime, endTime);
                log.info("[{}] {} {} {}ms", request.getMethod(), request.getRequestURI(), response != null
                    ? response.getStatus()
                    : "N/A", timeTaken.toMillis());
            }
        }
    }
}
