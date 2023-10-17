package com.raisei.hello.config;

import com.raisei.hello.bean.Cat;
import com.raisei.hello.bean.Car;
import com.raisei.hello.bean.User;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.*;

/**
 * 1、配置类里面使用@Bean标注在方法上给容器注册组件，默认也是单实例的
 * 2、配置类本身也是组件
 * 3、proxyBeanMethods：代理bean的方法
 *      Full(proxyBeanMethods = true)、【保证每个@Bean方法被调用多少次返回的组件都是单实例的】
 *      Lite(proxyBeanMethods = false)【每个@Bean方法被调用多少次返回的组件都是新创建的】
 *      组件依赖必须使用Full模式默认。其他默认是否Lite模式
 */

//@Conditional("") //条件装配
//@Import({User.class,Cat.class}) //给容器中自动创建出这两个类型的组件、默认组件的名字就是全类名
@Configuration(proxyBeanMethods = true)  //告诉SpringBoot这是一个配置类 == 配置文件
//@ImportResource("classpath:beans.xml")//导入原生bean文件

//开启user属性配置功能，即使user中的@ConfigurationProperties(prefix = "user22")生效
// 把user注册到组件中
@EnableConfigurationProperties({Car.class,User.class})
//@ConfigurationProperties(prefix = "user22")
public class MyConfig {
//    @ConditionalOnMissingBean(name = "tom")
//    @ConditionalOnBean()
    @Bean//给容器中添加组件。以方法名作为组件的id。返回类型就是组件类型。返回的值，就是组件在容器中的实例
    public User user01(){
        return new User("张三",18,cat01());
    }
    @Bean("tom")
    public Cat cat01(){
        return new Cat("tomcat");
    }

}
