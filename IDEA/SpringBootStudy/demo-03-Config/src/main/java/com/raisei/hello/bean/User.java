package com.raisei.hello.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ConfigurationProperties(prefix = "user22")
public class User {
    private String name;
    private int age;
    private Cat cat;

}
