package com.demo.bean;

import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @ClassName AreaDocument
 * @Description TODO
 * @Author Jay.Jia
 * @Date 2020/5/17 23:21
 * @Version 1.0
 */
@Builder
@Document(collection = "areas")
public class AreaDocument {

    @Id
    private Integer _id;

    private String areaid;

    private String area;

    private String cityid;
}
