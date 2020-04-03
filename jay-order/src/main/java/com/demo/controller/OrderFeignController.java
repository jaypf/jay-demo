package com.demo.controller;

import com.demo.feign.OrderApiService;

/**
 * @ClassName OrderFeignController
 * @Description TODO
 * @Author Jay.Jia
 * @Date 2020/4/1 21:30
 * @Version 1.0
 */
public class OrderFeignController implements OrderApiService {

    @Override
    public String test1(Integer ms) {
        return "I am come from jay-service-api:OrderFeignController.test1()";
    }
}
