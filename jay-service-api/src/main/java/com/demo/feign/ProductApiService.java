package com.demo.feign;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @ClassName ProductApiService
 * @Description feign调用product服务公共接口(客户端根据路径匹配)
 * @Author Jay.Jia
 * @Date 2020/4/1 17:45
 * @Version 1.0
 */
@RequestMapping("/feign")
public interface ProductApiService {

    @RequestMapping(value = "/v1/getAllProduct", method = RequestMethod.GET)
    String getAllProduct(@RequestParam("ms") Integer ms);

}
