package com.demo.service.impl;

import com.demo.vo.ResponseBean;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class NormalSearch {

    @Resource
    private RestHighLevelClient restHighLevelClient;

    @Resource
    private SendSearchRequest sendSearchRequest;
    /**
     * get kibana_sample_data_flights/_search
     * {
     * 	"from":100,
     * 	"size":20,
     * 	"query":{
     * 		"match_all":{}
     *        },
     * 	"_source":["Origin*","*Weather"],
     * 	"sort":[{"DistanceKilometers":"asc"},{"FlightNum":"desc"}]
     * }
     * @param indexName
     * @return
     */
    public ResponseBean searchExample(String indexName) {
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices(indexName);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.from(0);
        searchSourceBuilder.size(5);
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        String[] includeFields = new String[]{"Origin*","*Weather"};
        searchSourceBuilder.fetchSource(includeFields,null);
        searchSourceBuilder.sort(new FieldSortBuilder("DistanceKilometers")
                .order(SortOrder.ASC)

        );
        searchSourceBuilder.sort(new FieldSortBuilder("FlightNum")
                .order(SortOrder.DESC)

        );
        searchRequest.source(searchSourceBuilder);
        return sendSearchRequest.send(searchRequest, RequestOptions.DEFAULT);
    }
}
