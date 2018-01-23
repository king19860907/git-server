package com.majun.git.servlet;

import com.majun.git.factory.MyReceivePackFactory;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

/**
 * Created by majun on 24/01/2018.
 */
public class GitServlet extends org.eclipse.jgit.http.server.GitServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        setReceivePackFactory(new MyReceivePackFactory());
        super.init(config);
    }
}
