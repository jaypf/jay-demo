package com.demo.mapper;

import com.demo.domain.BankAEntity;
import feign.Param;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

public interface BankAEntityMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BankAEntity record);

    int insertOrUpdate(BankAEntity record);

    int insertOrUpdateSelective(BankAEntity record);

    int insertSelective(BankAEntity record);

    BankAEntity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BankAEntity record);

    int updateByPrimaryKey(BankAEntity record);

    List<BankAEntity> selectByAll(BankAEntity bankAEntity);

    int updateBatch(List<BankAEntity> list);

    int updateBatchSelective(List<BankAEntity> list);

    int batchInsert(@Param("list") List<BankAEntity> list);
}