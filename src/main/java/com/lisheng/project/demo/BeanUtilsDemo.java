package com.lisheng.project.demo;

import com.lisheng.project.bean.BeanUtils;

/**
 * @ClassName： BeanUtilsDemo
 * @description:
 * @author: 李胜
 * @create: 2020-06-06 15:12
 **/
public class BeanUtilsDemo {
    public static void main(String[] args) throws Exception {
        DemoA a=new DemoA();
        a.setAge("12");
        a.setName("13");
        a.setPass("14");
        DemoB b=new DemoB();
        BeanUtils.objectTranserToObejct(a, b);
        System.out.println(b);
    }

}
