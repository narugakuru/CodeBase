package com.raisei.pojo;

import lombok.Data;

@Data
public class User {
    private Integer id;
    private String username;
    private Integer age;
    private String address;

    public User(Integer id, String username, Integer age, String address) {
        this.id = id;
        this.username = username;
        this.age = age;
        this.address = address;
    }
}
