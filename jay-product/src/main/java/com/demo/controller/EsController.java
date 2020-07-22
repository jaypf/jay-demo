package com.demo.controller;

import com.demo.service.EsServive;
import com.demo.service.impl.NormalSearch;
import com.demo.service.impl.OperateDoc;
import com.demo.service.impl.OperateIndex;
import com.demo.vo.ResponseBean;
import com.demo.vo.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @ClassName ProductEsController
 * @Description TODO
 * @Author Jay.Jia
 * @Date 2020/7/21 17:14
 * @Version 1.0
 */
@Api(value = "Product-es", tags = {"Product-es"})
@RestController
@RequestMapping("/es")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET,
        RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
public class EsController {

    @Resource
    private EsServive esServive;
    @Resource
    private OperateIndex operateIndex;
    @Resource
    private OperateDoc operateDoc;
    @Resource
    private NormalSearch normalSearch;

    /**Kibana飞行数据*/
    private final static String KIBANA_SAMPLE_DATA_FLIGHTS = "kibana_sample_data_flights";
    /**Kibana飞行数据日志*/
    private final static String KIBANA_SAMPLE_DATA_LOGS  = "kibana_sample_data_logs";

    @ApiOperation(value = "es测试是否存在索引接口", notes = "es测试是否存在索引接口")
    @RequestMapping(value = "/index/existence", method = RequestMethod.POST)
    public String indexExists(@RequestParam String indexName) {
//        boolean isExists = esServive.isIndexExists(indexName);
        boolean isExists = operateIndex.isIndexExists(indexName);
        String msg = isExists? "索引存在："+indexName : "索引不存在："+indexName ;
        return msg;
    }

    @ApiOperation(value = "es测试创建索引接口", notes = "es测试创建索引接口")
    @RequestMapping(value = "index/creation",method = RequestMethod.POST)
    public String createIndex(@RequestParam String indexName){
        try {
//            esServive.createIndex(indexName);
            operateIndex.createIndex(indexName);
        } catch (IOException e) {
            e.printStackTrace();
            return "创建失败";
        }
        return "创建成功";
    }

    @ApiOperation(value = "es测试删除索引接口", notes = "es测试删除索引接口")
    @RequestMapping(value = "/index/erasure", method = RequestMethod.POST)
    public String deleteIndex(@RequestParam String indexName) {
//        boolean isDelete = esServive.deleteIndex(indexName);
        boolean isDelete = operateIndex.deleteIndex(indexName);
        if (isDelete) {
            return "删除成功";
        } else {
            return "删除失败";
        }
    }


    @ApiOperation(value = "es测试插入文档接口", notes = "es测试插入文档接口")
    @RequestMapping(value = "/doc/insertion", method = RequestMethod.POST)
    public ResponseBean insertDoc(@RequestBody User user,
                                  @RequestParam String indexName,
                                  @RequestParam  String docId) {
        return operateDoc.insertDoc(user,indexName,docId);
    }

    @ApiOperation(value = "es测试获取文档接口", notes = "es测试插入文档接口")
    @RequestMapping(value = "/doc/query", method = RequestMethod.GET)
    public ResponseBean getDoc(@RequestParam String indexName,
                               @RequestParam String docId) {
        return operateDoc.getDoc(indexName,docId);
    }

    @ApiOperation(value = "es测试更新文档接口", notes = "es测试插入文档接口")
    @RequestMapping(value = "/doc/update", method = RequestMethod.POST)
    public ResponseBean updateDoc(@RequestParam String indexName,
                                  @RequestParam String docId,
                                  @RequestParam String fieldName,
                                  @RequestParam String fieldValue) {
        return operateDoc.updateDoc(indexName,docId,fieldName,fieldValue);
    }

    @ApiOperation(value = "es测试删除文档接口", notes = "es测试插入文档接口")
    @RequestMapping(value = "/doc/erasure", method = RequestMethod.POST)
    public ResponseBean deleteDoc(@RequestParam String indexName,
                                  @RequestParam String docId) {
        return operateDoc.deleteDoc(indexName,docId);
    }

    @ApiOperation(value = "_search接口基本用法", notes = "search接口基本用法")
    @RequestMapping(value = "/search/example", method = RequestMethod.POST)
    public ResponseBean searchExample() {
        return normalSearch.searchExample(KIBANA_SAMPLE_DATA_FLIGHTS);
    }
//
//    @ApiOperation(value = "基于词项的查询", notes = "基于词项的term查询")
//    @RequestMapping(value = "/search/term", method = RequestMethod.POST)
//    public ResponseBean termsBasedSearch() {
//        return normalSearch.termsBasedSearch(KIBANA_SAMPLE_DATA_FLIGHTS,
//                "dayOfWeek");
//    }
//
//    @ApiOperation(value = "基于全文的查询", notes = "基于全文的multi_match查询")
//    @RequestMapping(value = "/search/match", method = RequestMethod.POST)
//    public ResponseBean matchBasedSearch() {
//        return normalSearch.matchBasedSearch(KIBANA_SAMPLE_DATA_FLIGHTS,
//                "AT","DestCountry", "OriginCountry");
//    }
//
//    @ApiOperation(value = "基于全文的模糊查询", notes = "基于全文的模糊查询")
//    @RequestMapping(value = "/search/fuzzy", method = RequestMethod.POST)
//    public ResponseBean fuzzySearch() {
//        return normalSearch.fuzzySearch(KIBANA_SAMPLE_DATA_LOGS,
//                "message","firefix");
//    }
//
//    @ApiOperation(value = "组合查询范例", notes = "组合查询之bool查询")
//    @RequestMapping(value = "/search/combination-bool",
//            method = RequestMethod.POST)
//    public ResponseBean combinationSearch() {
//        return normalSearch.boolSearch(KIBANA_SAMPLE_DATA_LOGS);
//    }
//
//    @ApiOperation(value = "聚集查询范例", notes = "聚集查询范例")
//    @RequestMapping(value = "/search/aggsExample", method = RequestMethod.POST)
//    public ResponseBean aggsExampleSearch() {
//        return aggsSearch.aggsExampleSearch(KIBANA_SAMPLE_DATA_FLIGHTS);
//    }

}
