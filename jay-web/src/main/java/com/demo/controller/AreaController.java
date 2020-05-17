package com.demo.controller;

import com.demo.domain.GAreasEntity;
import com.demo.service.AeraService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
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
    @Autowired
    RedisTemplate redisTemplate;
    @ApiOperation(value="省列表")
    @RequestMapping(value = "/v1/test1", method = RequestMethod.GET)
    public String getProvinces(){
        return "I am AreaController.getProvinces()";
    }


    @ApiOperation(value="区县列表")
    @RequestMapping(value = "/v1/aeras", method = RequestMethod.GET)
    public List<GAreasEntity> getAeras(){
        List<GAreasEntity> gAreasEntities = aeraService.qryAreas();
        return gAreasEntities;
    }

    @ApiOperation(value="区县")
    @RequestMapping(value = "/v1/aera", method = RequestMethod.GET)
    public String getArea(Integer id){
        aeraService.qryArea(id);
        Object o = redisTemplate.opsForValue().get("areaCache::area:1");
        System.out.println(o.toString());
        return "aeraService.qryArea(id)";
    }

    @ApiOperation(value="mongo")
    @RequestMapping(value = "/v1/mongo", method = RequestMethod.GET)
    public String mongo(Integer id){
        aeraService.mongo(id);
        return "mongo";
    }

}
