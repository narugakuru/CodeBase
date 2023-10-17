/*
package com.raisei.bootdemo01.config;

import com.raisei.bootdemo01.interceptor.LoginInterceptor;
import com.raisei.bootdemo01.interceptor.RedisUriCountInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AdminWebConfig implements WebMvcConfigurer {

    @Autowired
    RedisUriCountInterceptor redisUriCountInterceptor;


//    添加拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //登录拦截
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/**")//拦截所有请求
                .excludePathPatterns("/","/login","/js/**","/css/**","/images/**","/fonts/**");//放行的请求,静态资源

        //计数统计
        registry.addInterceptor(redisUriCountInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/","/login","/js/**","/css/**","/images/**","/fonts/**");

    }
}
*/
