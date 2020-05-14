package com.demo.service.impl;

import com.demo.datasource.TargetDataSource;
import com.demo.domain.GAreasEntity;
import com.demo.mapper.GAreasEntityMapper;
import com.demo.service.AeraService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @TargetDataSource(name = "ds1")
    @Override
    public List<GAreasEntity> qryArea() {
        return gAreasEntityMapper.selectByAll(null);
    }
}
