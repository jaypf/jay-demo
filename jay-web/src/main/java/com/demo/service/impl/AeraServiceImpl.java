package com.demo.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.demo.datasource.TargetDataSource;
import com.demo.domain.GAreasEntity;
import com.demo.mapper.GAreasEntityMapper;
import com.demo.service.AeraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

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
    RedisTemplate redisTemplate;

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
        //redisTemplate.opsForValue().increment("aa", Long.parseLong("1"));
        redisTemplate.opsForValue().setIfAbsent("aa", "asasfesf");
        redisTemplate.opsForValue().setIfAbsent("a1", gAreasEntity.toString());
        redisTemplate.opsForValue().setIfAbsent("a2", result);
        return result;
    }
}
