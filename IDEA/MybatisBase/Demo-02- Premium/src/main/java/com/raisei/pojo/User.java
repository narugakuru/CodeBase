package com.raisei.pojo;

import lombok.Data;

import java.util.List;

@Data
public class User {
    private Integer id;
    private String username;
    private Integer age;
    private String address;
    private List<Role> roles;

    public User() {
    }

    public User(Integer id, String username, Integer age, String address, List<Role> roles) {
        this.id = id;
        this.username = username;
        this.age = age;
        this.address = address;
        this.roles = roles;
    }
}
