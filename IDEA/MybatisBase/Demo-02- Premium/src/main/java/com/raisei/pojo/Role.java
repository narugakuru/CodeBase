package com.raisei.pojo;

import lombok.Data;

@Data
public class Role {
    private Integer id;
    private String name;
    private String desc;

    public Role() {
    }

    public Role(Integer id, String name, String desc) {
        this.id = id;
        this.name = name;
        this.desc = desc;
    }
}
