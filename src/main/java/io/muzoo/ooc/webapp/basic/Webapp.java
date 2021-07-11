package io.muzoo.ooc.webapp.basic;

import io.muzoo.ooc.webapp.basic.servlets.ServletRouter;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.apache.tomcat.util.descriptor.web.ErrorPage;

import javax.servlet.ServletException;
import java.io.File;
import java.sql.*;

public class Webapp {

    public static void main(String[] args) {
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8082);

        File doceBase = new File("src/main/webapp/");
        doceBase.mkdirs();

        try {
            Context ctx = tomcat.addWebapp("", doceBase.getAbsolutePath());

            ServletRouter servletRouter = new ServletRouter();
            servletRouter.init(ctx);
            ErrorPage errorPage = new ErrorPage();
            errorPage.setErrorCode(404);
            errorPage.setLocation("/WEB-INF/error404.jsp");
            ctx.addErrorPage(errorPage);

            tomcat.start();
            tomcat.getServer().await();
        } catch (LifecycleException e) {
            e.printStackTrace();
        }

    }
}
