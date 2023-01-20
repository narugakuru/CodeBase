package com.raisei;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RedirectDemo extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*
        resp.setHeader("Location","r/make");
        resp.setStatus(302);
        */
        resp.sendRedirect("/r/make");//重定向
        /*重定向，转发区别
        相同：页面跳转
        不同：
            请求转发,url不变
            重定向url改变*/
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
