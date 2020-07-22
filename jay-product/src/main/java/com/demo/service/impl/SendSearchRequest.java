package com.demo.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.demo.vo.ResponseBean;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;

@Service
public class SendSearchRequest {

    @Resource
    private RestHighLevelClient restHighLevelClient;

    /*
    * 一般来说，非聚集的检索只需处理hits部分，直接将结果返回给前端
    * */
    public ResponseBean send(SearchRequest searchRequest,
                             RequestOptions options){
        JSONArray jsonArray = new JSONArray();
        try {
            sendAndProcessHits(searchRequest,options,jsonArray);
            return new ResponseBean(200,"查询文档",jsonArray);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseBean(200,"查询文档失败",null);
        }

    }


    /*处理返回结果中的hits部分*/
    public SearchResponse sendAndProcessHits(SearchRequest searchRequest,
                                   RequestOptions options,
                                   JSONArray jsonArray) throws IOException {

        SearchResponse search = restHighLevelClient.search(searchRequest, options);
        SearchHits hits = search.getHits();
        for(SearchHit hit:hits){
            String src = hit.getSourceAsString();
            JSONObject jsonObject = JSON.parseObject(src);
            jsonArray.add(jsonObject);
        }
        return search;
    }

}
