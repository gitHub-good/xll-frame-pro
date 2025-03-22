package com.xll.frame.system.srv.config.log;

import com.xll.frame.starter.log.core.annotation.ConditionalOnEnabledLog;
import com.xll.frame.starter.log.core.dao.LogDao;
import com.xll.frame.starter.system.domain.system.UserService;
import com.xll.frame.starter.system.infrastructure.mapper.LogMapper;
import com.xll.frame.starter.web.autoconfigure.trace.TraceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 功能描述: <br>
 * <p>
 *  <日志配置>
 * </p>
 * @author xuliangliang
 * @since 2025/3/23 01:27
 * @version 1.0.0
 */
@Configuration
@ConditionalOnEnabledLog
public class LogConfiguration {

    /**
     * 日志持久层接口本地实现类
     */
    @Bean
    public LogDao logDao(UserService userService, LogMapper logMapper, TraceProperties traceProperties) {
        return new LogDaoLocalImpl(userService, logMapper, traceProperties);
    }
}
