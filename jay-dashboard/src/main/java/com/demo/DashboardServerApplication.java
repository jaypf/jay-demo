package com.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * @ClassName DashboardApplication
 * @Description
 * 监控界面：http://localhost:本服务port/hystrix
 * 需要监控的端点（使用了hystrix组件的端点）：http://localhost:目标port/actuator/hystrix.stream
 * @Author Jay.Jia
 * @Date 2020/3/28 0:32
 * @Version 1.0
 */
@Slf4j
@SpringBootApplication
@EnableHystrixDashboard
public class DashboardServerApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(DashboardServerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("================DASHBOARD-SERVER START SUCCESS!!!=====================");
    }
}
