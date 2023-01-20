package com.raisei.CookieTest;

import com.raisei.pojo.Person;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class SessionSet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        解决中文乱码
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
//        获取session
        HttpSession session = req.getSession();
//        给session注入信息
        session.setAttribute("person",new Person("小岚岚",19,"男"));
        System.out.println("person已存入");
//        获取session的id
        String sessionId = session.getId();
//        判断session是否为新的
        if(session.isNew()){
            resp.getWriter().write("session创建成功,ID:"+sessionId);
        }else{
            resp.getWriter().write("session已存在,ID:"+sessionId);
        }
/*      session在创建时,做了什么?
        Cookie cookie = new Cookie("JSESSIONID",sessionId);
        resp.addCookie(cookie);
*/
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
