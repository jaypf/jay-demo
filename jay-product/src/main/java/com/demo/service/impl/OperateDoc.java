package com.demo.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.demo.vo.ResponseBean;
import com.demo.vo.User;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.get.GetResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;

@Service
public class OperateDoc {

    @Resource
    private RestHighLevelClient restHighLevelClient;

    public ResponseBean insertDoc(User user, String indexName, String docId) {
        IndexRequest indexRequest = new IndexRequest(indexName);
        String userJson = JSONObject.toJSONString(user);
        if(!docId.equals("noid")){
            indexRequest.id(docId);
        }
        indexRequest.source(userJson, XContentType.JSON);
        try {
            IndexResponse indexResponse =
                    restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
            if(indexResponse!=null){
                String id = indexResponse.getId();
                String index = indexResponse.getIndex();
                if(indexResponse.getResult() == DocWriteResponse.Result.CREATED){
                    System.out.println("新增文档成功");
                    return  new ResponseBean(200,"索引文档成功",id);
                }else if(indexResponse.getResult() == DocWriteResponse.Result.UPDATED){
                    System.out.println("覆盖文档成功");
                    return  new ResponseBean(200,"覆盖文档成功",null);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ResponseBean getDoc(String indexName, String docId) {
        return  new ResponseBean(200,"参考TestEsHighSdk",indexName+docId);
    }

    public ResponseBean updateDoc(String indexName, String docId, String fieldName,
                                  String fieldValue) {
        try {
            XContentBuilder xContentBuilder = XContentFactory.jsonBuilder();
            xContentBuilder.startObject();
            {
                xContentBuilder.field(fieldName,fieldValue);
                //xContentBuilder.timeField()
            }
            xContentBuilder.endObject();
            UpdateRequest request =
                    new UpdateRequest(indexName, docId).doc(xContentBuilder);
            request.docAsUpsert(true);
            request.fetchSource(true);/*在应答里包含当前文档的内容*/
            UpdateResponse updateResponse =
                    restHighLevelClient.update(request, RequestOptions.DEFAULT);
            GetResult getResult = updateResponse.getGetResult();
            if(getResult.isExists()){
                String sourceAsString = getResult.sourceAsString();
                return new ResponseBean(200,"更新文档成功",sourceAsString);
            }else{
                return new ResponseBean(200,"更新文档失败",
                        indexName+"/"+docId+"/"+fieldName);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ResponseBean deleteDoc(String indexName, String docId) {
        DeleteRequest deleteRequest = new DeleteRequest(indexName, docId);
        try {
            DeleteResponse deleteResponse =
                    restHighLevelClient.delete(deleteRequest, RequestOptions.DEFAULT);
            if(deleteResponse.getResult() == DocWriteResponse.Result.NOT_FOUND){
                return new ResponseBean(200,"删除不存在的文档",
                        indexName+"/"+docId);
            }else{
                return new ResponseBean(200,"删除文档成功",
                        indexName+"/"+docId);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
