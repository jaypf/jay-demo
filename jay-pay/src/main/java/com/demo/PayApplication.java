package com.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @ClassName PayApplication
 * @Description TODO
 * @Author Jay.Jia
 * @Date 2020/3/18 23:16
 * @Version 1.0
 */
@Slf4j
@SpringBootApplication
@EnableEurekaClient
public class PayApplication implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(PayApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("================WEB-SERVER START SUCCESS!!!=====================");
    }
}
