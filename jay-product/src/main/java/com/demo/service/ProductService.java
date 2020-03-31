package com.demo.service;

/**
 * @ClassName ProductService
 * @Description TODO
 * @Author Jay.Jia
 * @Date 2020/3/25 23:01
 * @Version 1.0
 */
public interface ProductService {

    /**
     * @Description  测试ribbon
     * @Param []
     * @Author Jay.Jia
     * @Date 2020/3/30 22:10
     * @return java.lang.String
     **/
    String test1();

    /**
     * @Description 测试ffeign
     * @Param []
     * @Author Jay.Jia
     * @Date 2020/3/30 22:10
     * @return java.lang.String
     **/
    String getAllProduct(Integer ms);

}
