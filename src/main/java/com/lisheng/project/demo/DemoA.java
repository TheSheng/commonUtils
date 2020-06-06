package com.lisheng.project.demo;

import com.lisheng.project.bean.Definition;

/**
 * @ClassName： DemoA
 * @description:
 * @author: 李胜
 * @create: 2020-06-06 15:12
 **/
public class DemoA {
    @Definition(targetName = "userName")
    private String name;
    private String pass;
    private String age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }


}
