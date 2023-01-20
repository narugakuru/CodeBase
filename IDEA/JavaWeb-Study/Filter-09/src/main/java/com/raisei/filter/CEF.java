package com.raisei.filter;

import javax.servlet.*;
import java.io.IOException;

public class CEF implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("CharacterEncodingFilter初始化");
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding("utf-8");
        servletResponse.setCharacterEncoding("utf-8");
        servletResponse.setContentType("text/html;charset=utf-8");

        System.out.println("CharacterEncodingFilter执行前...");
        filterChain.doFilter(servletRequest,servletResponse);//让请求继续走
        System.out.println("CharacterEncodingFilter执行后...");
    }

    public void destroy() {
        System.gc();
        System.out.println("CharacterEncodingFilter销毁");
    }
}
