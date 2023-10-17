package com.raisei.filter;

import com.raisei.util.constant;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class interceptDemo implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletRequest HttpServletResponse
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        if(httpRequest.getSession().getAttribute(constant.USER_SESSION)==null){
            httpResponse.sendRedirect("/s/error.jsp");
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    public void destroy() {

    }
}
