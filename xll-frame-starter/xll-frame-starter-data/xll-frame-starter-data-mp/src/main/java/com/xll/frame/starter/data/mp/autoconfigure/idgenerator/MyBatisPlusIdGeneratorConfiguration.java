package com.xll.frame.starter.data.mp.autoconfigure.idgenerator;

import cn.hutool.core.net.NetUtil;
import com.baomidou.mybatisplus.core.incrementer.DefaultIdentifierGenerator;
import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import lombok.extern.slf4j.Slf4j;
import me.ahoo.cosid.IdGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ResolvableType;

/**
 * 功能描述: <br>
 * <p>
 *  <MyBatis ID 生成器配置>
 * </p>
 * @author xuliangliang
 * @since 2025/2/6 21:36
 * @version 1.0.0
 */
@Slf4j
public class MyBatisPlusIdGeneratorConfiguration {

    private MyBatisPlusIdGeneratorConfiguration() {
    }

    /**
     * 自定义 ID 生成器-默认（雪花算法，使用网卡信息绑定雪花生成器，防止集群雪花 ID 重复）
     */
    @ConditionalOnMissingBean(IdentifierGenerator.class)
    @ConditionalOnProperty(name = "mybatis-plus.extension.id-generator.type", havingValue = "default", matchIfMissing = true)
    public static class Default {
        static {
            log.debug("[ContiNew Starter] - Auto Configuration 'MyBatis Plus-IdGenerator-Default' completed initialization.");
        }

        @Bean
        public IdentifierGenerator identifierGenerator() {
            return new DefaultIdentifierGenerator(NetUtil.getLocalhost());
        }
    }

    /**
     * 自定义 ID 生成器-CosId
     */
    @ConditionalOnMissingBean(IdentifierGenerator.class)
    @ConditionalOnClass(IdGenerator.class)
    @ConditionalOnProperty(name = "mybatis-plus.extension.id-generator.type", havingValue = "cosid")
    public static class CosId {
        static {
            log.debug("[Frame Starter] - Auto Configuration 'MyBatis Plus-IdGenerator-CosId' completed initialization.");
        }

        @Bean
        public IdentifierGenerator identifierGenerator() {
            return new MyBatisPlusCosIdIdentifierGenerator();
        }
    }

    /**
     * 自定义 ID 生成器
     */
    @ConditionalOnProperty(name = "mybatis-plus.extension.id-generator.type", havingValue = "custom")
    public static class Custom {
        @Bean
        @ConditionalOnMissingBean
        public IdentifierGenerator identifierGenerator() {
            if (log.isErrorEnabled()) {
                log.error("Consider defining a bean of type '{}' in your configuration.", ResolvableType
                    .forClass(IdentifierGenerator.class));
            }
            throw new NoSuchBeanDefinitionException(IdentifierGenerator.class);
        }
    }
}