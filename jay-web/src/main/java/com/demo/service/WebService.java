package com.demo.service;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName WebService
 * @Description TODO
 * @Author Jay.Jia
 * @Date 2020/3/25 22:32
 * @Version 1.0
 */
public interface WebService {

    /**
     * @Description 测试web服务
     * @Param []
     * @Author Jay.Jia
     * @Date 2020/3/30 23:50
     * @return java.lang.String
     **/
    String test1();

    /**
     * @Description ribbon调用product服务
     * @Param [path] product服务路径
     * @Author Jay.Jia
     * @Date 2020/3/30 23:50
     * @return java.lang.String
     **/
    String test2(String path);

    /**
     * @Description 测试HystrixCommand
     * @Param [path]
     * @Author Jay.Jia
     * @Date 2020/3/30 23:51
     * @return java.lang.String
     **/
    String test3(String path);

    /**
     * @Description fegin调用product
     * @Param [ms] product睡眠时间
     * @Author Jay.Jia
     * @Date 2020/3/30 23:52
     * @return java.lang.String
     **/
    String test4(Integer ms);

    /**
     * @Description fegin调用order
     * @Param [ms] product睡眠时间
     * @Author Jay.Jia
     * @Date 2020/3/30 23:52
     * @return java.lang.String
     **/
    String test5(Integer ms);

    /**
     * @Description ribbon调用order服务，测试oauth2密码验证
     * @Param [ms] order睡眠时间
     * @Author Jay.Jia
     * @Date 2020/3/30 23:52
     * @return java.lang.String
     **/
    String test6(HttpServletRequest request);
}
