package com.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @ClassName AuthorJWTServerApplication
 * @Description
 * JWT：json web token 是一种无状态的权限认证方式，一般用于前后端分离，时效性比较短的权限校验，
 * jwt模式获取token跟客户端、密码、授权码模式是一样的，只是需要配置秘钥：
 * 1、 到 jdk 的 bin 目录执行该指令，会在 bin 目录下生成 jay-jwt.jks 文件，把该文件放到本服务工程里面的 resources 目录下
 *  keytool -genkeypair -alias jay-jwt -validity 3650 -keyalg RSA -dname "CN=jwt,OU=jtw,O=jwt,L=zurich,S=zurich, C=CH" -keypass 123456 -keystore jay-jwt.jks -storepass 123456
 * 2、生成公钥: keytool -list -rfc --keystore jay-jwt.jks | openssl x509 -inform pem -pubkey
 *  把生成的公钥内容放到 public.cert 文件中,再把该公钥文件放到客户端的 resources 目录下
 * @Author Jay.Jia
 * @Date 2020/4/11 22:19
 * @Version 1.0
 */
@Slf4j
@SpringBootApplication
@EnableEurekaClient
public class AuthorJWTServerApplication implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(AuthorJWTServerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("================AUTHOR_SECURITY-SERVER START SUCCESS!!!=====================");
    }
}
