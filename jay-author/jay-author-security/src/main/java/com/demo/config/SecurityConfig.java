package com.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * @ClassName SecurityConfig
 * @Description 配置spring security
 * @Author Jay.Jia
 * @Date 2020/4/6 5:37
 * @Version 1.0
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 在做AuthorizationServerConfigurer配置时使用
     * @Param []
     * @Author Jay.Jia
     * @Date 2020/4/6 5:39
     * @return org.springframework.security.authentication.AuthenticationManager
     **/
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        AuthenticationManager manager = super.authenticationManagerBean();
        return manager;
    }

    /**
     * 配置用户
     * 这里使用内存中的用户，实际项目中，一般使用的是数据库保存用户，具体的实现类可以使用JdbcDaoImpl或者JdbcUserDetailsManager
     * @Param []
     * @Author Jay.Jia
     * @Date 2020/4/6 5:40
     * @return org.springframework.security.core.userdetails.UserDetailsService
     **/
    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        //对密码进行加密
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String finalPassword = "{bcrypt}" + bCryptPasswordEncoder.encode("123456");
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("jay").password(finalPassword).authorities("USER").build());
        manager.createUser(User.withUsername("admin").password(finalPassword).authorities("USER").build());

        return manager;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(new BCryptPasswordEncoder());;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
//        http.requestMatchers().anyRequest()
//                .and()
//                .authorizeRequests()
//                .antMatchers("/oauth/**").permitAll();

        http.authorizeRequests()
                .antMatchers("/oauth/**","/actuator/**").permitAll()
                .and()
                .httpBasic().disable();
    }
}
