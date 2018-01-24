package com.majun.git.servlet;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static javax.servlet.http.HttpServletResponse.SC_NOT_FOUND;

/**
 * Created by majun on 24/01/2018.
 */
public class GitServlet extends org.eclipse.jgit.http.server.GitServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        getDelegateFilter().doFilter(req, res, new FilterChain() {
            @Override
        public void doFilter(ServletRequest request,
                ServletResponse response) throws IOException,
                ServletException {
            ((HttpServletResponse) response).sendError(SC_NOT_FOUND);
        }
    });
    }


}
