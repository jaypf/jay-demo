package com.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/**
 * @ClassName AuthorSecurityDBServerApplication
 * @Description
 * 官方提供的oauth2相关的数据库脚本https://github.com/zg-jack/zg-config-repo/blob/master/config-repo/api-gateway.properties
 * 其中把LONGVARBINARY类型改为了BLOB
 * @Author Jay.Jia
 * @Date 2020/4/7 23:15
 * @Version 1.0
 */
@Slf4j
@SpringBootApplication
@EnableEurekaClient
//EnableResourceServer注解开启资源服务，因为程序需要对外暴露获取token的API和验证token的API所以该程序也是一个资源服务器
@EnableResourceServer
public class AuthorSecurityDBServerApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(AuthorSecurityDBServerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("================AUTHOR_SECURITY_DB-SERVER START SUCCESS!!!=====================");
    }
}
