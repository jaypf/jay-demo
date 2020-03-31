package com.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @ClassName OrderApplication
 * @Description TODO
 * @Author Jay.Jia
 * @Date 2020/3/18 23:15
 * @Version 1.0
 */
@Slf4j
@SpringBootApplication
@EnableEurekaClient
public class OrderServerApplication implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(OrderServerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("================ORDER-SERVER START SUCCESS!!!=====================");
    }
}
