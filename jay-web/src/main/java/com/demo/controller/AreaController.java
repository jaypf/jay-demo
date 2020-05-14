package com.demo.controller;

import com.demo.domain.GAreasEntity;
import com.demo.service.AeraService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @Autowired
    private AeraService aeraService;

    @ApiOperation(value="省列表")
    @RequestMapping(value = "/v1/test1", method = RequestMethod.GET)
    public String getProvinces(){
        return "I am AreaController.getProvinces()";
    }


    @ApiOperation(value="区县列表")
    @RequestMapping(value = "/v1/aera", method = RequestMethod.GET)
    public List<GAreasEntity> getAera(){
        List<GAreasEntity> gAreasEntities = aeraService.qryArea();
        return gAreasEntities;
    }

}
