package com.demo.routeMapper;

import org.springframework.cloud.netflix.zuul.filters.discovery.PatternServiceRouteMapper;

/**
 * @ClassName PatternServiceRouteMapperConfig
 * @Description TODO
 * @Author Jay.Jia
 * @Date 2020/4/3 17:44
 * @Version 1.0
 */
//@Configuration
public class PatternServiceRouteMapperConfig {

    //    @Bean
    public PatternServiceRouteMapper patternServiceRouteMapper() {

        /*
         * servicePattern 指定微服务的正则
         * routePattern  指定路由正则
         * */
        return new PatternServiceRouteMapper("(?<name>^.+)-(?<version>v.+$)",
                "${version}/${name}");
    }

}
