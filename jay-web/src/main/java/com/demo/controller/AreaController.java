package com.demo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName AreaController
 * @Description
 * @Author Jay.Jia
 * @Date 2020/4/3 16:20
 * @Version 1.0
 */
@Api(value = "Area",tags={"地区"})
@RestController
@RequestMapping("/area")
public class AreaController {


    @ApiOperation(value="省列表")
    @RequestMapping(value = "/v1/test1", method = RequestMethod.GET)
    public String getProvinces(){
        return "I am AreaController.getProvinces()";
    }


}
