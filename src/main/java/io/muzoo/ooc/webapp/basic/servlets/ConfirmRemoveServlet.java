package io.muzoo.ooc.webapp.basic.servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ConfirmRemoveServlet extends AbstractRoutableHttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(securityService.isAuthorized(request)) {
            String username = request.getParameter("removeuser");
            request.setAttribute("removeuser", username);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/confirm.jsp");
            requestDispatcher.include(request, response);
        } else {
            response.sendRedirect("/login");
        }
    }

    @Override
    public String getPattern() {
        return "/confirmremove";
    }
}
