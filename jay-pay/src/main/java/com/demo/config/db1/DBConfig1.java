package com.demo.config.db1;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @ClassName DBConfig
 * @Description TODO
 * @Author Jay.Jia
 * @Date 2020/5/17 2:18
 * @Version 1.0
 */
@Data
@Configuration
@PropertySource(value = "classpath:application.yml")
@ConfigurationProperties(prefix = "spring.mysql.test1",ignoreInvalidFields = true)
public class DBConfig1 {
    private String url;
    // 比如这个url在properties中是这样子的mysql.datasource.test1.username = root
    private String username;
    private String password;
    private int minPoolSize;
    private int maxPoolSize;
    private int maxLifetime;
    private int borrowConnectionTimeout;
    private int loginTimeout;
    private int maintenanceInterval;
    private int maxIdleTime;
    private String testQuery;
}
