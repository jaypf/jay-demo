package com.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
    * 行政区域县区信息表
    */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GAreasEntity {
    private Integer id;

    private String areaid;

    private String area;

    private String cityid;
}