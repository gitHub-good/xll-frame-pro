package com.xll.frame.starter.cache.jetcache;

import com.xll.frame.starter.core.util.GeneralPropertySourceFactory;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
/**
 * 功能描述: <br>
 * <p>
 *  <JetCache 自动配置>
 * </p>
 * @author xuliangliang
 * @since 2025/3/23 01:09
 * @version 1.0.0
 */
@AutoConfiguration
@Import(com.alicp.jetcache.autoconfigure.JetCacheAutoConfiguration.class)
@PropertySource(value = "classpath:default-cache-jetcache.yml", factory = GeneralPropertySourceFactory.class)
public class JetCacheAutoConfiguration {

    private static final Logger log = LoggerFactory.getLogger(JetCacheAutoConfiguration.class);

    @PostConstruct
    public void postConstruct() {
        log.debug("[Frame Starter] - Auto Configuration 'JetCache' completed initialization.");
    }

}
