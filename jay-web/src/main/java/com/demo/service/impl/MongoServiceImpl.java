package com.demo.service.impl;

import com.demo.mapper.GAreasEntityMapper;
import com.demo.service.MongoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName MongoServiceImpl
 * implements MongoService<E> ***************不同的实体类型，使用同一个ServiceImpl处理
 * implements MongoService<GAreasEntity> ***************这种方式根据不同的实体类型，使用不同的ServiceImpl
 * 也可以在同一工具类里处理
 * @Description TODO
 * @Author Jay.Jia
 * @Date 2020/5/18 0:21
 * @Version 1.0
 */
@Slf4j
@Service
public class MongoServiceImpl<E> implements MongoService<E> {//***************不同的实体类型，使用同一个ServiceImpl处理
//public class MongoServiceImpl implements MongoService<GAreasEntity> {//***************这种方式根据不同的实体类型，使用不同的ServiceImpl

    @Autowired
    private MongoTemplate mongoTemplate;


    @Override
    public String save(E obj) {
        E save = mongoTemplate.save(obj);
        return save.toString();
    }

    @Override
    public List<E> findAll() {
        return null;
    }

    @Override
    public List<E> findAll(Class<E> c) {
        List<E> all = mongoTemplate.findAll(c);
        return all;
    }

    @Override
    public E getById(String id) {
        return null;
    }

    @Override
    public E getByName(String name) {
        return null;
    }

    @Override
    public String updateE(E e) {
        return null;
    }

    @Override
    public String deleteE(E e) {
        return null;
    }

    @Override
    public String deleteById(String id) {
        return null;
    }

    @Override
    public List<E> findLikes(String reg) {
        return null;
    }
}
