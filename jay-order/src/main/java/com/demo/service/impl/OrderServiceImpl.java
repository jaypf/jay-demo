package com.demo.service.impl;

import com.demo.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @ClassName OrderServiceImpl
 * @Description TODO
 * @Author Jay.Jia
 * @Date 2020/3/30 23:24
 * @Version 1.0
 */
@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    @Value("${server.port}")
    private String port;


    @Override
    public String test1(Integer ms){
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Hello Jay, I am order-server-port:"+port+",test1!";
    }



}
