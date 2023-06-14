package com.lyy.express.Entity;

import lombok.Data;

@Data
public class User {
    private int id;
    private String username;
    private String pwd;
    private String phone;
    private String birth;
    private String address;

    public User(int id, String username, String pwd, String phone, String birth, String address) {
        this.id = id;
        this.username = username;
        this.pwd = pwd;
        this.phone = phone;
        this.birth = birth;
        this.address = address;
    }
}
