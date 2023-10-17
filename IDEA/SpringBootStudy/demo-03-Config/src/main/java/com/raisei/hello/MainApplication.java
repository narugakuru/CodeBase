package com.raisei.hello;

import com.raisei.hello.bean.Cat;
import com.raisei.hello.bean.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.util.Arrays;

@SpringBootApplication
/*@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan("com.raisei")*/
public class MainApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(MainApplication.class, args);

//  获取组件名数组
        String[] beanDefinitionNames = run.getBeanDefinitionNames();
//        打印所有组件
        System.out.println("=======================");
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName);
        }
        System.out.println("=======================");

/*
        String[] beanNamesForType = run.getBeanNamesForType(User.class);
        System.out.println("user组件:"+Arrays.toString(beanNamesForType));

        Cat tom = run.getBean("tomcat11", Cat.class);
        User user = run.getBean("user11", User.class);
        System.out.println("user11的cat和cat组件相同:"+ (user.getCat()==tom));

//        System.out.println("user组件存在吗:"+run.containsBean("user01"));
*/

    }

}
