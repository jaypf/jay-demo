package com.demo.feign;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @ClassName OrderApiService
 * @Description TODO
 * @Author Jay.Jia
 * @Date 2020/4/1 21:23
 * @Version 1.0
 */
@RequestMapping("/feign")
public interface OrderApiService {

    @RequestMapping(value = "/v1/test1", method = RequestMethod.GET)
    String test1(@RequestParam("ms") Integer ms);

}
