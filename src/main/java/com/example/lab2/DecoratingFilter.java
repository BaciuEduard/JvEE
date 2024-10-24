package com.example.lab2;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.logging.LogRecord;

@WebFilter("/*")
public class DecoratingFilter implements Filter{

    private String prelude;
    private String coda;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        prelude = (String) servletRequest.getAttribute("prelude");
        coda = (String) servletRequest.getAttribute("coda");
        httpResponse.getWriter().println(prelude);
        filterChain.doFilter(servletRequest, servletResponse);
        httpResponse.getWriter().println(coda);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
