package io.muzoo.ooc.webapp.basic.servlets;

import io.muzoo.ooc.webapp.basic.security.UserNameNotUniqueException;
import io.muzoo.ooc.webapp.basic.security.UserServiceException;
import lombok.SneakyThrows;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddUserServlet extends AbstractRoutableHttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (securityService.isAuthorized(request)) {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/adduser.jsp");
            requestDispatcher.include(request, response);
            request.getSession().removeAttribute("error");
        } else {
            request.removeAttribute("error");
            response.sendRedirect("/login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String error = null;
        if (securityService.isAuthorized(request)) {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String confirmPassword = request.getParameter("confirmPassword");

            if (securityService.checkIfUserExists(username)) {
                error = "Username already existed.";
            } else if (!password.equals(confirmPassword)) {
                error = "The password confirmation does not match.";
            }

            if (error != null) {
                request.getSession().setAttribute("error", error);
            } else {
                try {
                    securityService.addUser(request);
                    response.sendRedirect("/");
                    return;
                } catch (Exception e) {
                    request.getSession().setAttribute("error", e.getMessage());
                }
            }
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/adduser.jsp");
            requestDispatcher.include(request, response);
        } else {
            request.removeAttribute("error");
            response.sendRedirect("/login");
        }
    }

    @Override
    public String getPattern() {
        return "/user/add";
    }
}
