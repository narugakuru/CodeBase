package com.raisei.servlet;

import com.raisei.util.constant;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.getRequestDispatcher()
        Object user_session = req.getSession().getAttribute(constant.USER_SESSION);
        if (user_session != null) {
            req.getSession().removeAttribute(constant.USER_SESSION);
            resp.sendRedirect("/s/index.jsp");
        } else {
            resp.sendRedirect("/s/index.jsp");
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
