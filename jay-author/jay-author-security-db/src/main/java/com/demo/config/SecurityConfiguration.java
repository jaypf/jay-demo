package com.demo.config;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @ClassName SecurityConfiguration
 * @Description TODO
 * @Author Jay.Jia
 * @Date 2020/4/7 23:20
 * @Version 1.0
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {


    @Test
    public void test1(){
        String a = "{bcrypt}" + new BCryptPasswordEncoder().encode("123456");
        System.out.println(a);
    }

    /**
     * @Description 数据库中直接保存加密后密码，这里配置密码加密器，对输入的明文密码加密后再与库中比对
     *
     * @Param []
     * @Author Jay.Jia
     * @Date 2020/4/7 23:41
     * @return org.springframework.security.crypto.password.PasswordEncoder
     **/
    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    /**
     * @Description 注入AuthenticationManager接口，启用OAuth2密码模式
     * @Param []
     * @Author Jay.Jia
     * @Date 2020/4/7 23:22
     * @return org.springframework.security.authentication.AuthenticationManager
     **/
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        AuthenticationManager manager = super.authenticationManagerBean();
        return manager;
    }

    /**
     * @Description 通过HttpSecurity实现Security的自定义过滤配置
     * @Param [http]
     * @Author Jay.Jia
     * @Date 2020/4/8 22:31
     * @return void
     **/
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.requestMatchers().anyRequest()
                .and()
                .authorizeRequests()
//                .antMatchers("/oauth/**").permitAll();
                .antMatchers("/").authenticated();

/*        http.csrf().disable().exceptionHandling()
                .authenticationEntryPoint((request, response, authException) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED))
                .and()
                .authorizeRequests().
                antMatchers("/favicon.ico").permitAll()
                .antMatchers("/oauth/**").permitAll()
                .antMatchers("/login/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .and()
                .httpBasic().disable();*/
        http.authorizeRequests().antMatchers("/**").fullyAuthenticated().and().httpBasic();  //拦截所有请求 通过httpBasic进行认证

    }

}
