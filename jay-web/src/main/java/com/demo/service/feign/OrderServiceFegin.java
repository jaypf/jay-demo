package com.demo.service.feign;

import com.demo.feign.OrderApiService;
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
@FeignClient(name = "JAY-ORDER"
        /*,path = "/order"不使用公共接口时使用*/
        /*fallback = ProductServiceFeignFallback.class,不能获取具体异常*/
        ,fallbackFactory = ServiceFeignFallbackFactory.class)
public interface OrderServiceFegin extends OrderApiService {


//    @RequestMapping(value = "/v1/test1", method = RequestMethod.GET)
//    String test1(@RequestParam("ms") Integer ms);


}
