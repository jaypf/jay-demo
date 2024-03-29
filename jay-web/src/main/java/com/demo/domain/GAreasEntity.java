package com.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
    * 行政区域县区信息表
    */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "areas")
public class GAreasEntity {
    @Id
    private Integer id;

    private String areaid;

    private String area;

    private String cityid;
}