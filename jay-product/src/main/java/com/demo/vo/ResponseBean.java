package com.demo.vo;

import lombok.Data;

/**
 * @ClassName ResponseBean
 * @Description 给前端页面返回的应答实体
 * @Author Jay.Jia
 * @Date 2020/7/22 17:48
 * @Version 1.0
 */
@Data
public class ResponseBean {
    /**状态码*/
    private Integer code;
    /**返回信息*/
    private String message;
    /**返回的数据*/
    private Object data;

    public ResponseBean(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
