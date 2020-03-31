package com.demo.service.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @ClassName ProductServiceFeign
 * @Description TODO
 * @Author Jay.Jia
 * @Date 2020/3/30 15:01
 * @Version 1.0
 */
@FeignClient(name = "JAY-PRODUCT",path = "/product"
        /*fallback = ProductServiceFeignFallback.class,不能获取具体异常*/
        ,fallbackFactory = ServiceFeignFallbackFactory.class)
public interface ProductServiceFeign {


    @RequestMapping(value = "/v1/getAllProduct", method = RequestMethod.GET)
    String getAllProduct(@RequestParam("ms") Integer ms);

}
