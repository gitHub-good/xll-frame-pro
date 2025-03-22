package com.xll.frame.starter.core.autoconfigure.threadpool;

import com.xll.frame.starter.core.constant.PropertiesConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.task.ThreadPoolTaskExecutorCustomizer;
import org.springframework.boot.task.ThreadPoolTaskSchedulerCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 功能描述: <br>
 * <p>
 *  <线程池自动配置>
 * </p>
 * @author xuliangliang
 * @since 2025/2/6 21:10
 * @version 1.0.0
 */
@Slf4j
@Lazy
@AutoConfiguration
@EnableConfigurationProperties(ThreadPoolExtensionProperties.class)
public class ThreadPoolAutoConfiguration {

    @Value("${spring.task.execution.pool.core-size:#{T(java.lang.Runtime).getRuntime().availableProcessors() + 1}}")
    private int corePoolSize;

    @Value("${spring.task.execution.pool.max-size:#{T(java.lang.Runtime).getRuntime().availableProcessors() * 2}}")
    private int maxPoolSize;

    /**
     * 异步任务线程池配置
     */
    @Bean
    @ConditionalOnProperty(prefix = "spring.task.execution.extension", name = PropertiesConstants.ENABLED, havingValue = "true", matchIfMissing = true)
    public ThreadPoolTaskExecutorCustomizer threadPoolTaskExecutorCustomizer(ThreadPoolExtensionProperties properties) {
        return executor -> {
            // 核心（最小）线程数
            executor.setCorePoolSize(corePoolSize);
            // 最大线程数
            executor.setMaxPoolSize(maxPoolSize);
            // 当线程池的任务缓存队列已满并且线程池中的线程数已达到 maxPoolSize 时采取的任务拒绝策略
            executor.setRejectedExecutionHandler(properties.getExecution()
                .getRejectedPolicy()
                .getRejectedExecutionHandler());
            log.debug("[ContiNew Starter] - Auto Configuration 'TaskExecutor' completed initialization.");
        };
    }

    /**
     * 定时任务线程池配置
     */
    @EnableScheduling
    @ConditionalOnProperty(prefix = "spring.task.scheduling.extension", name = PropertiesConstants.ENABLED, havingValue = "true", matchIfMissing = true)
    public static class TaskSchedulerConfiguration {
        @Bean
        public ThreadPoolTaskSchedulerCustomizer threadPoolTaskSchedulerCustomizer(ThreadPoolExtensionProperties properties) {
            return executor -> {
                executor.setRejectedExecutionHandler(properties.getScheduling()
                    .getRejectedPolicy()
                    .getRejectedExecutionHandler());
                log.debug("[ContiNew Starter] - Auto Configuration 'TaskScheduler' completed initialization.");
            };
        }
    }
}
