package com.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @ClassName ConfigServerApplication
 * @Description
 * post
 * 加密：http://localhost:9890/encrypt?data=123456
 * 解密：http://localhost:9890/decrypt
 * @Author Jay.Jia
 * @Date 2020/3/31 14:11
 * @Version 1.0
 */
@Slf4j
@SpringBootApplication
@EnableEurekaClient
@EnableConfigServer
public class ConfigServerApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ConfigServerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("================CONFIG-SERVER START SUCCESS!!!=====================");
    }

}
