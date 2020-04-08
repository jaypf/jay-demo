package com.demo.controller;

import com.demo.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName OrderController
 * @Description TODO
 * @Author Jay.Jia
 * @Date 2020/3/30 23:22
 * @Version 1.0
 */
@Slf4j
@Api(value = "订单", tags = "{订单}")
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @ApiOperation(value="/v1/order")
    @RequestMapping(value = "/v1/test1", method = RequestMethod.GET)
    public String test1(@RequestParam("ms") Integer ms){
        return orderService.test1(ms);
    }

    @ApiOperation(value="/v1/ouath")
    @RequestMapping(value = "/v1/ouath", method = RequestMethod.GET)
    public String test2(HttpServletRequest request){
        //log.info(""+this.hashCode());
        log.info("======OrderController.test2============已经调用==========" + request.getRemotePort());
        return "======OrderController.test2============已经调用==========";
    }

}
