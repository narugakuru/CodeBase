package com.raisei.listener;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class OLCountListener implements HttpSessionListener {
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        System.out.println("触发了session创建");
        ServletContext servletContext = httpSessionEvent.getSession().getServletContext();
        Integer onlineCount = (Integer) servletContext.getAttribute("OnlineCount");

        if (onlineCount == null) {
            onlineCount = new Integer(1);
        } else {
            onlineCount++;
        }
        servletContext.setAttribute("OnlineCount", onlineCount);
    }

    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        ServletContext servletContext = httpSessionEvent.getSession().getServletContext();
        Integer onlineCount = (Integer) servletContext.getAttribute("OnlineCount");

        if (onlineCount == null) {
            onlineCount = new Integer(0);
        } else {
            onlineCount--;
        }
        servletContext.setAttribute("OnlineCount", onlineCount);
    }
}
