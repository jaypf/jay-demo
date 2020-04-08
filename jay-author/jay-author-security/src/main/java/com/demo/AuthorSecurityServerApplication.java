package com.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/**
 * @ClassName AuthorSecurityServerApplication
 * @Description oauth2-客户端模式，使用该模式只用配置对应的客户端即可，根据不同的client信息获取对应的token
 * 客户端模式申请 token，只要带上有平台资质的客户端 id、客户端密码、然后 带上授权类型是客户端授权模式，带上 scope 就可以了。
 * 这里要注意的是客户端必须是具有资质的(我们的系统提前保存了改客户端的信息)。
 * @Author Jay.Jia
 * @Date 2020/4/6 4:54
 * @Version 1.0
 */
@Slf4j
@SpringBootApplication
@EnableEurekaClient
//EnableResourceServer注解开启资源服务，因为程序需要对外暴露获取token的API和验证token的API所以该程序也是一个资源服务器
@EnableResourceServer
public class AuthorSecurityServerApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(AuthorSecurityServerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("================AUTHOR_SECURITY-SERVER START SUCCESS!!!=====================");
    }
}
