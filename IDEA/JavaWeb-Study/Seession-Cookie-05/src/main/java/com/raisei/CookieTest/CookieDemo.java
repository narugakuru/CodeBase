package com.raisei.CookieTest;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

public class CookieDemo extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //解决乱码问题
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");

        PrintWriter out = resp.getWriter();
//        服务器从客户端获取cookie
        Cookie[] cookies = req.getCookies();

        if (cookies != null) {
            out.write("你上一次访问的时间是");
            for (Cookie cookie : cookies) {
//                获取cookie的名字
                if (cookie.getName().equals("lastLoginTime")) {
//                    获取cookie的值
                    Long lastLoginTime = Long.parseLong(cookie.getValue());
                    Date date = new Date(lastLoginTime);
                    out.write(date.toLocaleString());
                }
            }

        } else {
            out.write("这是你第一次访问本站");
        }

//        给客户端响应
        Cookie cookie = new Cookie("lastLoginTime", System.currentTimeMillis() + "");
//        设置Cookie
        cookie.setMaxAge(24*60*60);
        resp.addCookie(cookie);

    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
