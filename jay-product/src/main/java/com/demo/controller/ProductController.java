package com.demo.controller;

import com.demo.service.ProductService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName ProductController
 * @Description TODO
 * @Author Jay.Jia
 * @Date 2020/3/25 23:01
 * @Version 1.0
 */
@Api(value = "Product", tags = {"Product"})
@RestController
@RequestMapping("/product/v1")
public class ProductController {

    @Autowired
    private ProductService productService;


    @RequestMapping(value = "/test1", method = RequestMethod.GET)
    public String test1(){
        return productService.test1();
    }

    @RequestMapping(value = "/test2", method = RequestMethod.GET)
    public String test2(){
        return productService.test1();
    }


    @RequestMapping(value = "/getAllProduct", method = RequestMethod.GET)
    public String getAllProduct(@RequestParam("ms") Integer ms){
        return productService.getAllProduct(ms);
    }
}
