package com.demo.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.demo.bean.AreaDocument;
import com.demo.datasource.TargetDataSource;
import com.demo.domain.GAreasEntity;
import com.demo.mapper.GAreasEntityMapper;
import com.demo.service.AeraService;
import com.demo.service.MongoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName AeraServiceImpl
 * @Description TODO
 * @Author Jay.Jia
 * @Date 2020/5/14 18:48
 * @Version 1.0
 */
@Service
public class AeraServiceImpl implements AeraService {

    @Autowired
    private GAreasEntityMapper gAreasEntityMapper;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private MongoService mongoService;

    @TargetDataSource(name = "ds1")
    @Override
    public List<GAreasEntity> qryAreas() {
        return gAreasEntityMapper.selectByAll(GAreasEntity.builder().id(1).build());
    }

    @Cacheable(cacheNames = "areaCache", key = "'area:'+#id")
    @TargetDataSource(name = "ds1")
    @Override
    public String qryArea(Integer id) {
        GAreasEntity gAreasEntity = gAreasEntityMapper.selectByPrimaryKey(id);
        String result = JSONObject.toJSONString(gAreasEntity);
        //redis
        //redisTemplate.opsForValue().increment("aa", Long.parseLong("1"));
        redisTemplate.opsForValue().setIfAbsent("aa", "asasfesf", 1, TimeUnit.MINUTES);
        redisTemplate.opsForValue().setIfAbsent("a1", gAreasEntity.toString(), 1, TimeUnit.MINUTES);
        redisTemplate.opsForValue().setIfAbsent("a2", result, 1, TimeUnit.MINUTES);
        //mongodb
        AreaDocument build = AreaDocument.builder().build();
        BeanUtils.copyProperties(gAreasEntity, build);
        AreaDocument insert = mongoTemplate.insert(build);
        System.out.println(insert.toString());

        return result;
    }

    @Override
    public String mongo(Integer id) {
        List all = mongoService.findAll(GAreasEntity.class);
        return "all";
    }
}
