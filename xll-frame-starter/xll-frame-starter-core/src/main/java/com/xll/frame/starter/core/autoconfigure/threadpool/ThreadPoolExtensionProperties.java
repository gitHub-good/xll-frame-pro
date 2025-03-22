package com.xll.frame.starter.core.autoconfigure.threadpool;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 功能描述: <br>
 * <p>
 *  <线程池扩展配置属性>
 * </p>
 * @author xuliangliang
 * @since 2025/2/6 20:29
 * @version 1.0.0
 */
@Setter
@Getter
@ConfigurationProperties("spring.task")
public class ThreadPoolExtensionProperties {

    /**
     * 异步任务扩展配置属性
     */
    private ExecutorExtensionProperties execution = new ExecutorExtensionProperties();

    /**
     * 调度任务扩展配置属性
     */
    private SchedulerExtensionProperties scheduling = new SchedulerExtensionProperties();

    /**
     * 异步任务扩展配置属性
     */
    @Setter
    @Getter
    public static class ExecutorExtensionProperties {
        /**
         * 拒绝策略
         */
        private ThreadPoolExecutorRejectedPolicy rejectedPolicy = ThreadPoolExecutorRejectedPolicy.CALLER_RUNS;

    }

    /**
     * 调度任务扩展配置属性
     */
    @Setter
    @Getter
    public static class SchedulerExtensionProperties {
        /**
         * 拒绝策略
         */
        private ThreadPoolExecutorRejectedPolicy rejectedPolicy = ThreadPoolExecutorRejectedPolicy.CALLER_RUNS;

    }

}
