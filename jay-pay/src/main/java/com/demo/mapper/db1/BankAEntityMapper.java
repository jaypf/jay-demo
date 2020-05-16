package com.demo.mapper.db1;

import com.demo.domain.db1.BankAEntity;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

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

    int batchInsert(@Param("list") List<BankAEntity> list);

    //使用这种方式不会报org.apache.ibatis.binding.BindingException: Invalid bound statement (not found)异常
    @Select("select * from bank_a where id = #{id}")
    BankAEntity get(Long id);
}