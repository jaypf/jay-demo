package com.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

/**
 * @ClassName ZuulApplication
 * @Description TODO
 * @Author Jay.Jia
 * @Date 2020/3/27 12:01
 * @Version 1.0
 */
@Slf4j
@SpringBootApplication
@EnableZuulProxy
public class ZuulApplication implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(ZuulApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("================ZUUL-SERVER START SUCCESS!!!=====================");
    }

    @Bean
    @RefreshScope
    @ConfigurationProperties("zuul")
    @Primary
    public ZuulProperties zuulProperties(){
        return new ZuulProperties();
    }
}
