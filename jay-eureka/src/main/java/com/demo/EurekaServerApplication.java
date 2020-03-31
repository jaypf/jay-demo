package com.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @ClassName EurekaApplication
 * @Description TODO
 * @Author Jay.Jia
 * @Date 2020/3/18 22:04
 * @Version 1.0
 */
@Slf4j
@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApplication implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(EurekaServerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("================EUREKA-SERVER START SUCCESS!!!=====================");
    }
}
