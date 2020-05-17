package com.demo.service;

import com.demo.domain.GAreasEntity;

import java.util.List;

/**
 * @ClassName AeraService
 * @Description TODO
 * @Author Jay.Jia
 * @Date 2020/5/14 18:47
 * @Version 1.0
 */
public interface AeraService {

    /** 获取区县列表 */
    List<GAreasEntity> qryAreas();
    /** 获取区县 */
    String qryArea(Integer id);
    /** mongodb测试 */
    String mongo(Integer id);

}
