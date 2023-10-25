package com.hossi.recrute.common.filter;

import com.hossi.recrute.common.util.http.servlet.ServletHandler;
import jakarta.servlet.*;

import java.io.IOException;

public class AuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        ServletHandler servletHandler = ServletHandler.getINSTANCE();

    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
