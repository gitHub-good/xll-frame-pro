package com.xll.frame.starter.core.autoconfigure.project;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

/**
 * 功能描述: <br>
 * <p>
 *  <项目自动配置>
 * </p>
 * @author xuliangliang
 * @since 2025/2/6 20:20
 * @version 1.0.0
 */
@AutoConfiguration
@ComponentScan("cn.hutool.extra.spring")
@Import(cn.hutool.extra.spring.SpringUtil.class)
@EnableConfigurationProperties(ProjectProperties.class)
public class ProjectAutoConfiguration {}
