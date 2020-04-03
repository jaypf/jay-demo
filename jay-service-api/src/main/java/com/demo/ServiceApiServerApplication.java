package com.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @ClassName ClientApplication
 * @Description TODO
 * @Author Jay.Jia
 * @Date 2020/3/27 16:48
 * @Version 1.0
 */
@Slf4j
@SpringBootApplication
@EnableEurekaClient
public class ServiceApiServerApplication implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(ServiceApiServerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("================SERVICEAPI-SERVER START SUCCESS!!!=====================");
    }
}
