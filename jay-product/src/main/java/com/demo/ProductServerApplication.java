package com.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @ClassName ProductApplication
 * @Description TODO
 * @Author Jay.Jia
 * @Date 2020/3/18 23:06
 * @Version 1.0
 */
@Slf4j
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(basePackages = {"com.demo.service.feign"})
public class ProductServerApplication implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(ProductServerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("================PRODUCT-SERVER START SUCCESS!!!=====================");
    }
}
