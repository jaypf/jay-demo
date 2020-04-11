package com.demo.bean;

import lombok.Data;

/**
 * @ClassName AccessToken
 * @Description 获取Token返回的数据结构
 * @Author Jay.Jia
 * @Date 2020/4/9 23:44
 * @Version 1.0
 */
@Data
public class AccessToken {
    private String access_token;
    private String token_type;
    private String refresh_token;
    /** 剩余过期时间*/
    private int expires_in;
    private String scope;
}
