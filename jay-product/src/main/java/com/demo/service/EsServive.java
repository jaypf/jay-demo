package com.demo.service;

import java.io.IOException;

/**
 * @ClassName EsServive
 * @Description TODO
 * @Author Jay.Jia
 * @Date 2020/7/22 14:24
 * @Version 1.0
 */
public interface EsServive {

    /** 检查索引是否存在 */
    public boolean isIndexExists(String indexName);
    /** 创建索引 */
    public boolean createIndex(String indexName) throws IOException;
    /** 删除索引 */
    public boolean deleteIndex(String indexName);
}
