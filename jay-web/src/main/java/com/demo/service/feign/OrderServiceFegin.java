package com.demo.service.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @ClassName OrderServiceFegin
 * @Description TODO
 * @Author Jay.Jia
 * @Date 2020/3/30 23:29
 * @Version 1.0
 */
@FeignClient(name = "JAY-ORDER",path = "/order"
        /*fallback = ProductServiceFeignFallback.class,不能获取具体异常*/
        ,fallbackFactory = ServiceFeignFallbackFactory.class)
public interface OrderServiceFegin {


    @RequestMapping(value = "/v1/test1", method = RequestMethod.GET)
    String test1(@RequestParam("ms") Integer ms);


}
