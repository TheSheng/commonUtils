package com.lisheng.project.mybatis;

import java.lang.annotation.*;

/**
 *功能描述  代替page参数校验和startPage
 * @author 李胜
 * @date 2020/6/8

 **/
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Page {
}
