package com.xll.frame.system.srv;

import cn.dev33.satoken.annotation.SaIgnore;
import cn.hutool.core.net.NetUtil;
import cn.hutool.core.util.URLUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.alicp.jetcache.anno.config.EnableMethodCache;
import com.github.xiaoymin.knife4j.spring.configuration.Knife4jProperties;
import com.xll.frame.starter.core.autoconfigure.project.ProjectProperties;
import com.xll.frame.starter.extension.crud.core.annotation.EnableCrudRestController;
import com.xll.frame.starter.web.annotation.EnableGlobalResponse;
import com.xll.frame.starter.web.model.R;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dromara.x.file.storage.spring.EnableFileStorage;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * 功能描述: <br>
 * <p>
 *  <启动程序>
 * </p>
 * @author xuliangliang
 * @since 2025/3/23 01:54
 * @version 1.0.0
 */
@Slf4j
@EnableFileStorage
@EnableMethodCache(basePackages = "top.frame.admin")
@EnableGlobalResponse
@EnableCrudRestController
@RestController
@SpringBootApplication
@RequiredArgsConstructor
public class FrameAdminApplication implements ApplicationRunner {

    private final ProjectProperties projectProperties;
    private final ServerProperties serverProperties;

    public static void main(String[] args) {
        SpringApplication.run(FrameAdminApplication.class, args);
    }

    @Hidden
    @SaIgnore
    @GetMapping("/")
    public R index() {
        return R.ok("%s service started successfully.".formatted(projectProperties.getName()), null);
    }

    @Override
    public void run(ApplicationArguments args) {
        String hostAddress = NetUtil.getLocalhostStr();
        Integer port = serverProperties.getPort();
        String contextPath = serverProperties.getServlet().getContextPath();
        String baseUrl = URLUtil.normalize("%s:%s%s".formatted(hostAddress, port, contextPath));
        log.info("----------------------------------------------");
        log.info("{} service started successfully.", projectProperties.getName());
        log.info("API地址：{}", baseUrl);
        Knife4jProperties knife4jProperties = SpringUtil.getBean(Knife4jProperties.class);
        if (!knife4jProperties.isProduction()) {
            log.info("API文档：{}/doc.html", baseUrl);
        }
        log.info("----------------------------------------------");
    }
}
