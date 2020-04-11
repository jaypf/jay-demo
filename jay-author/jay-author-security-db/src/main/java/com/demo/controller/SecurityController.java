package com.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @ClassName SecurityController
 * @Description 认证服务器 token 校验和校验结果返回接口
 * 下游服务器进行调用需要权限校验的接口时会被被调用方ouath2拦截器携带请求的token转发过来进行token验证
 * @Author Jay.Jia
 * @Date 2020/4/8 1:20
 * @Version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/security")
public class SecurityController {

    @RequestMapping(value = "/check", method = RequestMethod.GET)
    public Principal getUser(Principal principal) {
        log.info("security server check================>>>" + principal.toString());
        return principal;
    }
}
