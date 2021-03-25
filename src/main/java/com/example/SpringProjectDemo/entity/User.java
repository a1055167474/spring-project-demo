package com.example.SpringProjectDemo.entity;


import java.io.Serializable;

public class User implements Serializable {

    private Long id; //主键id

    private String userName;//登录名

    private String password;//密码


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
