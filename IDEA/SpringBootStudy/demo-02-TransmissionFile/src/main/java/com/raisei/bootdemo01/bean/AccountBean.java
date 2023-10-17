package com.raisei.bootdemo01.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountBean {
    private Integer id;
    private String name;
    private String account;
    private String password;
}
