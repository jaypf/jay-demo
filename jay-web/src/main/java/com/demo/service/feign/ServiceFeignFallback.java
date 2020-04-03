package com.demo.service.feign;

import org.springframework.stereotype.Service;

/**
 * @ClassName ProductServiceFeignFallback
 * @Description TODO
 * @Author Jay.Jia
 * @Date 2020/3/30 15:04
 * @Version 1.0
 */
//@Service//feign中配置fallback后使用
public class ServiceFeignFallback implements ProductServiceFeign{

    @Override
    public String getAllProduct(Integer ms) {
        return "获取商品失败！！！";
    }
}
