package com.xll.frame.starter.common.config.mybatis;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.xll.frame.starter.extension.data.permission.core.filter.DataPermissionUserContextProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 功能描述: <br>
 * <p>
 *  <MyBatis Plus 配置>
 * </p>
 * @author xuliangliang
 * @since 2025/3/23 00:01
 * @version 1.0.0
 */
@Configuration
public class MybatisPlusConfiguration {

    /**
     * 元对象处理器配置（插入或修改时自动填充）
     */
    @Bean
    public MetaObjectHandler metaObjectHandler() {
        return new MyBatisPlusMetaObjectHandler();
    }

    /**
     * 数据权限用户上下文提供者
     */
    @Bean
    public DataPermissionUserContextProvider dataPermissionUserContextProvider() {
        return new DefaultDataPermissionUserContextProvider();
    }

    /**
     * BCrypt 加/解密处理器
     */
    @Bean
    public BCryptEncryptor bCryptEncryptor(PasswordEncoder passwordEncoder) {
        return new BCryptEncryptor(passwordEncoder);
    }
}
