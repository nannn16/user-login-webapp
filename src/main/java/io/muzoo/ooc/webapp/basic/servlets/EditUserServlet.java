package io.muzoo.ooc.webapp.basic.servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditUserServlet extends AbstractRoutableHttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(securityService.isAuthorized(request)) {
            String id = request.getParameter("id");
            String username = request.getParameter("username");
            String name = request.getParameter("name");
            request.setAttribute("id", id);
            request.setAttribute("username", username);
            request.setAttribute("name", name);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/edituser.jsp");
            requestDispatcher.include(request, response);
        } else {
            response.sendRedirect("/login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        securityService.editUser(request);
        response.sendRedirect("/");
    }

    @Override
    public String getPattern() {
        return "/edituser";
    }
}
