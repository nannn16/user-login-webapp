package io.muzoo.ooc.webapp.basic.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RemoveUserServlet extends AbstractRoutableHttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(securityService.isAuthorized(request)) {
            securityService.removeUser(request);
            response.sendRedirect("/");
        } else {
            response.sendRedirect("/login");
        }
    }

    @Override
    public String getPattern() {
        return "/removeuser";
    }
}
