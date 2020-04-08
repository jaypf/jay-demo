package com.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @ClassName SecurityController
 * @Description 认证服务器 token 校验和校验结果返回接口
 * @Author Jay.Jia
 * @Date 2020/4/6 6:34
 * @Version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/security")
public class SecurityController {

    @RequestMapping(value = "/check", method = RequestMethod.GET)
    public Principal getUser(Principal principal) {
        log.info("Token验证:"+principal.toString());
        return principal;
    }

}
