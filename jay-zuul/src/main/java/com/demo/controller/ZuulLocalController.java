package com.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName ZuulLocalController
 * @Description TODO
 * @Author Jay.Jia
 * @Date 2020/4/3 16:36
 * @Version 1.0
 */
@RestController
@RequestMapping("/local")
public class ZuulLocalController {

    @RequestMapping(value = "/v1/zuulLocal")
    public String test1(){
        return "I am ZuulLocalController. test1()";
    }
}
