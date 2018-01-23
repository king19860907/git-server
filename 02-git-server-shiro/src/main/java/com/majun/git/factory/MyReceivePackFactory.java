package com.majun.git.factory;

import org.eclipse.jgit.http.server.resolver.DefaultReceivePackFactory;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.transport.ReceivePack;
import org.eclipse.jgit.transport.resolver.ServiceNotAuthorizedException;
import org.eclipse.jgit.transport.resolver.ServiceNotEnabledException;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by majun on 24/01/2018.
 */
public class MyReceivePackFactory extends DefaultReceivePackFactory {

    public ReceivePack create(HttpServletRequest request, Repository repository) throws ServiceNotEnabledException, ServiceNotAuthorizedException {

       /* MessageBytes authorization =
                request.getCoyoteRequest().getMimeHeaders()
                        .getValue("authorization");*/
        /*ServletRequestWrapper request1 = (ServletRequestWrapper) request;
        RequestFacade request2 = (RequestFacade)request1.getRequest();
        Enumeration e = request.getHeaderNames();
        System.out.println(request2);
        //System.out.println(request1.getCoyoteRequest().getMimeHeaders

        throw new ServiceNotAuthorizedException();*/
        System.out.println(request.getRequestURI());
        return super.create(request,repository);
    }
}
