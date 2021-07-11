package io.muzoo.ooc.webapp.basic.servlets;

import io.muzoo.ooc.webapp.basic.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomeServlet extends AbstractRoutableHttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (securityService.isAuthorized(request)) {
            String username = securityService.getCurrentUsername(request);
            request.setAttribute("username", username);
            List<User> users = securityService.listUsers();
            request.setAttribute("users", users);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/home.jsp");
            requestDispatcher.include(request, response);
            request.getSession().removeAttribute("error");
        } else {
            request.removeAttribute("error");
            response.sendRedirect("/login");
        }
    }

    @Override
    public String getPattern() {
        return "/index.jsp";
    }
}
