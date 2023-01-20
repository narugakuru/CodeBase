package com.raisei.bootdemo01.config;

import com.raisei.bootdemo01.servlet.MyFilter;
import com.raisei.bootdemo01.servlet.MyServlet;
import com.raisei.bootdemo01.servlet.MyServletContextListener;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.servlet.WebListenerRegistrar;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration(proxyBeanMethods = true)
public class MyRegisterConfig {

    @Bean
    public ServletRegistrationBean<MyServlet> myServlet(){
        MyServlet myServlet = new MyServlet();
        return new ServletRegistrationBean<>(myServlet,"/my","/my02");
    }

    @Bean
    public FilterRegistrationBean<MyFilter> myFilter(){
        MyFilter myFilter = new MyFilter();
        FilterRegistrationBean<MyFilter> filterRegistrationBean = new FilterRegistrationBean<>(myFilter);
        filterRegistrationBean.setUrlPatterns(Arrays.asList("/my","/css/*"));
        return filterRegistrationBean;
    }

    @Bean
    public ServletListenerRegistrationBean<MyServletContextListener> myListener(){

        return new ServletListenerRegistrationBean<>(new MyServletContextListener());
    }

}
