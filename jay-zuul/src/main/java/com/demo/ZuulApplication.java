package com.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @ClassName ZuulApplication
 * @Description TODO
 * @Author Jay.Jia
 * @Date 2020/3/27 12:01
 * @Version 1.0
 */
@Slf4j
@SpringBootApplication
public class ZuulApplication implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(ZuulApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("================ZUUL-SERVER START SUCCESS!!!=====================");
    }
}
