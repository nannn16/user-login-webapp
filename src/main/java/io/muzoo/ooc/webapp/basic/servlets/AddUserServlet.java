package io.muzoo.ooc.webapp.basic.servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddUserServlet extends AbstractRoutableHttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(securityService.isAuthorized(request)) {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/adduser.jsp");
            requestDispatcher.include(request, response);
        } else {
            response.sendRedirect("/login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String error = "";
        if(securityService.addUser(request)) {
            response.sendRedirect("/");
        }
        else {
            error = "Username already existed.";

            request.setAttribute("error", error);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/adduser.jsp");
            requestDispatcher.include(request, response);
        }
    }

    @Override
    public String getPattern() {
        return "/adduser";
    }
}
