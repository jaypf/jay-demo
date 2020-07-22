package com.demo.vo;

import lombok.Data;

/**
 * @ClassName User
 * @Description es保存文档的实体
 * @Author Jay.Jia
 * @Date 2020/7/22 17:51
 * @Version 1.0
 */
@Data
public class User {
    private String firstName;

    private String secondName;

    private String content;

    private Integer age;
}
