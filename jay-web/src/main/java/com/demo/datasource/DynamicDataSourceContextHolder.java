package com.demo.datasource;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName DynamicDataSourceContextHolder
 * 数据源线程上下文对象
 * 建立映射关系，建立当前用户操作线程和数据源标识的映射关系
 * @Author Jay.Jia
 * @Date 2020/5/14 17:48
 * @Version 1.0
 */
public class DynamicDataSourceContextHolder {

    private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();

    public static ThreadLocal<String> getContextHolder() {
        return contextHolder;
    }

    public static List<String> dataSourceIds = new ArrayList<String>();

    public static void setDataSourceType(String dataSourceType) {
        contextHolder.set(dataSourceType);
    }

    public static String getDataSourceType() {
        return contextHolder.get();
    }

    public static boolean containsDataSource(String dataSourceId) {
        return dataSourceIds.contains(dataSourceId);
    }
}
