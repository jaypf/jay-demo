package com.demo.test;

import com.demo.bean.AreaDocument;
import com.demo.domain.GAreasEntity;
import com.demo.mapper.GAreasEntityMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * @ClassName MyTest
 * @Description TODO
 * @Author Jay.Jia
 * @Date 2020/5/17 23:17
 * @Version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringbootTest.class)
@WebAppConfiguration
public class MyTest {

    @Autowired
    private GAreasEntityMapper gAreasEntityMapper;

    @Autowired
    private MongoTemplate mongoTemplate;


    /**
     * 插入重复数据
     * 　　insert: 若新增数据的主键已经存在，则会抛 org.springframework.dao.DuplicateKeyException 异常提示主键重复，不保存当前数据。
     * 　　save: 若新增数据的主键已经存在，则会对当前已经存在的数据进行修改操作。
     *
     * 批操作
     * 　　insert: 可以一次性插入一整个列表，而不用进行遍历操作，效率相对较高
     * 　　save: 需要遍历列表，进行一个个的插入
     * @Param []
     * @Author Jay.Jia
     * @Date 2020/5/18 0:14
     * @return void
     **/
    @Test
    public void insertDemo(){
        GAreasEntity gAreasEntity = gAreasEntityMapper.selectByPrimaryKey(1);
        AreaDocument build = AreaDocument.builder().build();
        GAreasEntity build1 = GAreasEntity.builder().build();
        BeanUtils.copyProperties(gAreasEntity, build);
        BeanUtils.copyProperties(gAreasEntity, build1);


        AreaDocument insert = mongoTemplate.insert(AreaDocument.builder()._id(gAreasEntity.getId())
                .area(gAreasEntity.getArea()).areaid(gAreasEntity.getAreaid())
                .cityid(gAreasEntity.getCityid()).build());
        System.out.println(insert.toString());
        GAreasEntity save = mongoTemplate.save(gAreasEntity);
        System.out.println(save.toString());
    }

}
