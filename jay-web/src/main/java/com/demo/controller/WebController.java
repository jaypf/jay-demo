package com.demo.controller;

import com.demo.service.WebService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * @ClassName WebController
 * @Description TODO
 * @Author Jay.Jia
 * @Date 2020/3/25 22:32
 * @Version 1.0
 */
@Slf4j
@Api(value = "web",tags={"web"})
@RestController
@RequestMapping("/web")
public class WebController {

    @Autowired
    private WebService webService;

    @ApiOperation(value="web自跑")
    @RequestMapping(value = "/v1/test1", method = RequestMethod.GET)
    public String test1(){
        return webService.test1();
    }

    @ApiOperation(value="2 product")
    @RequestMapping(value = "/v1/test2", method = RequestMethod.GET)
    public String test2(@RequestParam("path") String path){
        return webService.test2(path);
    }

    @ApiOperation(value="HystrixCommand")
    @RequestMapping(value = "/v1/test3", method = RequestMethod.GET)
    public String test3(@RequestParam("path") String path){
        return webService.test3(path);
    }

    @ApiOperation(value="feign2Product")
    @RequestMapping(value = "/v1/test4", method = RequestMethod.GET)
    public String test4(@RequestParam("ms") Integer ms){
        return webService.test4(ms);
    }

    @ApiOperation(value="feign2Order")
    @RequestMapping(value = "/v1/test5", method = RequestMethod.GET)
    public String test5(@RequestParam("ms") Integer ms){
        return webService.test5(ms);
    }

    @ApiOperation(value="oauth2密码验证")
    @RequestMapping(value = "/v1/test6", method = RequestMethod.GET)
    public String test6(HttpServletRequest request){
        request.getCookies();
        Enumeration<String> headerNames = request.getHeaderNames();
        while(headerNames.hasMoreElements()) {
            String headername = headerNames.nextElement();
            log.info("header name = >" + headername + "= >" + "headervalue = " + request.getHeader(headername));
        }
        return webService.test6(request);
    }
}
