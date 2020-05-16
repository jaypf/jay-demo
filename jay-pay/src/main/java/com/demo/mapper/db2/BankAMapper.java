package com.demo.mapper.db2;

import com.demo.domain.db2.BankA;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BankAMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BankA record);

    int insertOrUpdate(BankA record);

    int insertOrUpdateSelective(BankA record);

    int insertSelective(BankA record);

    BankA selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BankA record);

    int updateByPrimaryKey(BankA record);

    int updateBatch(List<BankA> list);

    int batchInsert(@Param("list") List<BankA> list);
}