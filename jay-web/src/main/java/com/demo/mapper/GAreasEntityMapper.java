package com.demo.mapper;

import com.demo.domain.GAreasEntity;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

public interface GAreasEntityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GAreasEntity record);

    int insertOrUpdate(GAreasEntity record);

    int insertOrUpdateSelective(GAreasEntity record);

    int insertSelective(GAreasEntity record);

    GAreasEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GAreasEntity record);

    int updateByPrimaryKey(GAreasEntity record);

    int updateBatch(List<GAreasEntity> list);

    int updateBatchSelective(List<GAreasEntity> list);

    int batchInsert(@Param("list") List<GAreasEntity> list);

    List<GAreasEntity> selectByAll(GAreasEntity gAreasEntity);
}