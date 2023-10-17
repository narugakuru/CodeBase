package com.raisei.CookieTest;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;

public class CookieZH extends HttpServlet {
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
            out.write("?");
            for (Cookie cookie : cookies) {
//                获取cookie的名字
                if (cookie.getName().equals("name")) {
                    String s = URLDecoder.decode(cookie.getValue(), "utf-8");
                    out.write(s);
                }
            }

        } else {
            out.write("这是你第一次访问本站");
        }

        Cookie cookie = new Cookie("name","小岚岚");

//        设置Cookie
        cookie.setMaxAge(24*60*60);
        resp.addCookie(cookie);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
