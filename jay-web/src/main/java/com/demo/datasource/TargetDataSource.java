package com.demo.datasource;

import java.lang.annotation.*;

/**
 * @ClassName TargetDataSource
 * @Description TODO
 * @Author Jay.Jia
 * @Date 2020/5/14 18:07
 * @Version 1.0
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TargetDataSource {
    String name();
}
