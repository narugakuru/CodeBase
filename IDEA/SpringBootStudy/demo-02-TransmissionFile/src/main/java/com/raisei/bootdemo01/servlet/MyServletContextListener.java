package com.raisei.bootdemo01.servlet;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@Slf4j
//@WebListener
public class MyServletContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        log.info("listener初始化");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        log.info("listener销毁");
    }
}
