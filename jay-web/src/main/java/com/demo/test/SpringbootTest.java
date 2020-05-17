package com.demo.test;

import com.demo.config.DruidConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @ClassName SpringbootTest
 * @Description TODO
 * @Author Jay.Jia
 * @Date 2020/5/17 23:49
 * @Version 1.0
 */
@SpringBootApplication(scanBasePackages = {"com.demo"})
@MapperScan("com.demo.mapper")
@ServletComponentScan(basePackages = {"com.demo"})
@EnableConfigurationProperties(DruidConfig.class)
public class SpringbootTest extends SpringBootServletInitializer {

    /*
     * 1、要完成Spring容器的启动
     * 2、把项目部署到tomcat
     * */
    public static void main(String[] args) {
        SpringApplication.run(SpringbootTest.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(SpringBootTest.class);
    }

}
