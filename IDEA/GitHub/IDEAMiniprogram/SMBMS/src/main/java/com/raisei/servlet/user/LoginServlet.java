package com.raisei.servlet.user;

import com.raisei.pojo.User;
import com.raisei.service.UserService;
import com.raisei.service.UserServiceImpl;
import com.raisei.util.Constants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("LoginServlet--start...");
//        获取用户输入的信息
        String userCode = req.getParameter("userCode");
        String password = req.getParameter("userPassword");
//        与数据库的密码对比,调用业务层
        UserService userService = new UserServiceImpl();
        User loginUser = userService.login(userCode, password);
        if (loginUser != null) {
//            将user信息存入session，跳转到主页
            req.getSession().setAttribute(Constants.USER_SESSION, loginUser);
            resp.sendRedirect("jsp/frame.jsp");
        } else {
//            resp.sendRedirect("login.jsp");
            req.setAttribute("error","用户名或密码错误");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
