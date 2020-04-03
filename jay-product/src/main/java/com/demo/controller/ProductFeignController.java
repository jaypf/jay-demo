package com.demo.controller;

import com.demo.feign.ProductApiService;
import com.demo.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName ProductApiFeignController
 * @Description TODO
 * @Author Jay.Jia
 * @Date 2020/4/1 18:12
 * @Version 1.0
 */
@Slf4j
@RestController
public class ProductFeignController implements ProductApiService {

    @Autowired
    private ProductService productService;

    @Override
    public String getAllProduct(@RequestParam("ms") Integer ms) {
        return "I am come from jay-service-api:ProductFeignController.getAllProduct()";
    }
}
