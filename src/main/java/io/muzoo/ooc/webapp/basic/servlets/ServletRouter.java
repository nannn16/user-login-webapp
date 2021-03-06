package io.muzoo.ooc.webapp.basic.servlets;

import io.muzoo.ooc.webapp.basic.security.DatabaseConnectionService;
import io.muzoo.ooc.webapp.basic.security.SecurityService;
import io.muzoo.ooc.webapp.basic.security.UserService;
import io.muzoo.ooc.webapp.basic.security.UserServiceException;
import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class ServletRouter {
    
    private final List<Class<? extends AbstractRoutableHttpServlet>> servletClasses = new ArrayList<>();
    {
        servletClasses.add(HomeServlet.class);
        servletClasses.add(LoginServlet.class);
        servletClasses.add(LogoutServlet.class);
        servletClasses.add(AddUserServlet.class);
        servletClasses.add(RemoveUserServlet.class);
        servletClasses.add(ConfirmRemoveServlet.class);
        servletClasses.add(EditUserServlet.class);
        servletClasses.add(ChangePasswordServlet.class);
    }

    public void init(Context ctx) {
        UserService userService = new UserService();
        userService.setDatabaseConnectionService(new DatabaseConnectionService());
        SecurityService securityService = new SecurityService();
        securityService.setUserService(userService);

        for (Class<? extends AbstractRoutableHttpServlet> servletClass: servletClasses) {
            try {
                AbstractRoutableHttpServlet httpServlet = servletClass.getDeclaredConstructor().newInstance();
                httpServlet.setSecurityService(securityService);
                Tomcat.addServlet(ctx, servletClass.getSimpleName(), httpServlet);
                ctx.addServletMappingDecoded(httpServlet.getPattern(), servletClass.getSimpleName());
            } catch (InstantiationException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }
}
