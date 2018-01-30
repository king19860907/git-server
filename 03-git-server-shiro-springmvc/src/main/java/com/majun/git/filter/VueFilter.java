package com.majun.git.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by majun on 27/01/2018.
 */
public class VueFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest)request;
        HttpServletResponse resp = (HttpServletResponse)response;

        System.out.println(req.getServletPath());
        System.out.println(req.getRequestURI());
        String uri = req.getRequestURI();
        if(uri.contains(".") || uri.contains("/api/")){
            chain.doFilter(request,response);
            return;
        }
        request.getRequestDispatcher("/").forward(request,response);

    }

    public static void main(String[] args) {
        String uri = "/";
        System.out.println(uri.endsWith("[A-Z a-z/]"));
    }

    @Override
    public void destroy() {

    }
}
