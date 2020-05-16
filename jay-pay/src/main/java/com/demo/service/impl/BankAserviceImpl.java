package com.demo.service.impl;

import com.demo.domain.db1.BankAEntity;
import com.demo.domain.db2.BankA;
import com.demo.mapper.db2.BankAMapper;
import com.demo.mapper.db1.BankAEntityMapper;
import com.demo.service.BankAservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.jta.JtaTransactionManager;

import java.util.List;

/**
 * @ClassName BankAserviceImpl
 * @Description TODO
 * @Author Jay.Jia
 * @Date 2020/5/17 2:29
 * @Version 1.0
 */
@Service
public class BankAserviceImpl implements BankAservice {

    @Autowired
    private BankAEntityMapper bankAEntityMapper;
    @Autowired
    private BankAMapper bankAMapper;
    @Autowired
    TransactionManager transactionManager;

    /**
     * Atomikos 是一个基于 XA 协议的分布式事务解决管理框架，其核心思想就是两段提交，
     * ********************************************************************************，
     * ********《一般使用在在一个业务方法里面涉及到的对多个数据源的操作》********************，
     * ********************************************************************************，
     * 要保证在这个业务方法操作中，保持对两个数据源的同时提交和同时回滚
     * @Param [money]
     * @Author Jay.Jia
     * @Date 2020/5/17 4:22
     * @return java.lang.String
     **/
    @Override
    @Transactional
    public String transfer(Long money) {
        System.out.println(transactionManager);
        JtaTransactionManager jtaTransactionManager = (JtaTransactionManager) this.transactionManager;
        System.out.println(jtaTransactionManager.getUserTransaction());
        int i = bankAEntityMapper.updateByPrimaryKeySelective(BankAEntity.builder().id(2L).money(0-money).build());
        int i1 = bankAMapper.updateByPrimaryKeySelective(BankA.builder().id(2L).money(money).build());
        if (money > 100){throw new RuntimeException();}
        return "transfer ok:"+ i +"->"+i1;
    }
}
