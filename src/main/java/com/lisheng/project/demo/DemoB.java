package com.lisheng.project.demo;

/**
 * @ClassName： DemoB
 * @description:
 * @author: 李胜
 * @create: 2020-06-06 15:16
 **/
public class DemoB {
    private String userName;


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "DemoB{" +
            "userName='" + userName + '\'' +
            '}';
    }
}
