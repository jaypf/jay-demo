package com.demo.service.impl;

import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.support.IndicesOptions;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;

@Service
public class OperateIndex {

    @Resource
    private RestHighLevelClient restHighLevelClient;

    public boolean createIndex(String indexName) throws IOException {
        XContentBuilder xContentBuilder = XContentFactory.jsonBuilder()
                .startObject()
                    .field("properties").startObject()
                        .field("firstName").startObject()
                            .field("type","keyword")
                        .endObject()
                        .field("secondName").startObject()
                            .field("type","keyword")
                        .endObject()
                        .field("age").startObject()
                            .field("type","integer")
                        .endObject()
                        .field("content").startObject()
                            .field("type","text")
                        .endObject()
                    .endObject()
                .endObject();

        CreateIndexRequest createIndexRequest = new CreateIndexRequest(indexName);
        createIndexRequest.mapping(xContentBuilder);
        CreateIndexResponse createIndexResponse
                = restHighLevelClient.indices().create(createIndexRequest,
                RequestOptions.DEFAULT);
        boolean acknowledeg = createIndexResponse.isAcknowledged();
        return acknowledeg;

    }

    public boolean isIndexExists(String indexName) {
        GetIndexRequest getIndexRequest = new GetIndexRequest(indexName);
        getIndexRequest.humanReadable(true);
        boolean exists =   false;
        try {
            exists = restHighLevelClient.indices().exists(getIndexRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return exists;
    }

    public boolean deleteIndex(String indexName) {
        DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest(indexName);
        deleteIndexRequest.indicesOptions(IndicesOptions.LENIENT_EXPAND_OPEN);
        boolean acknowledged = false;
        try {
            AcknowledgedResponse delete =
                    restHighLevelClient.indices().delete(deleteIndexRequest,
                            RequestOptions.DEFAULT);
            acknowledged = delete.isAcknowledged();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return acknowledged;
    }



}
