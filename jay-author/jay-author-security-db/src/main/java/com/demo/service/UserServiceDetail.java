package com.demo.service;

import com.demo.dao.UserRepository;
import com.demo.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @ClassName UserServiceDetail
 * @Description 实现UserDetailsService接口，从数据库中获取用户信息判断是否给予授权
 * @Author Jay.Jia
 * @Date 2020/4/7 23:46
 * @Version 1.0
 */
@Slf4j
@Service
public class UserServiceDetail implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("===================获取到token已进入自定义验证："+username);
        User user = userRepository.findByUsername(username);
        log.info("授权用户信息:"+user.toString());
        return user;
    }
}