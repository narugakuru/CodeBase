package com.raisei.bootdemo01.bean;


import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @TableField(exist = false)
    private String userName;
    @TableField(exist = false)
    private String Password;

    private Integer id;
    private String name;
    private String age;
    private String email;
}
