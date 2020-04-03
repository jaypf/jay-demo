package com.demo.service.impl;

import com.demo.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

/**
 * @ClassName ProductServiceImpl
 * @Description TODO
 * @Author Jay.Jia
 * @Date 2020/3/25 23:01
 * @Version 1.0
 */
@Slf4j
@Service("productServiceImpl")
public class ProductServiceImpl implements ProductService {

    @Value("${server.port}")
    private String port;
    @Value("${product.productName:}")
    private String productName;

    @Override
    public String test1(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Hello Jay, I am product-server-port:"+port+",test1!";
    }

    @Override
    public String getAllProduct(Integer ms) {
        log.info("getAllProduct is start!");
        if (ObjectUtils.isEmpty(ms)){
            //用来测试feign拦截器
           int error = 1 / 0;
        }
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("getAllProduct is end!");
        return "Hello Jay, I am product-server-port:"+port+",getAllProduct!";
    }

}
