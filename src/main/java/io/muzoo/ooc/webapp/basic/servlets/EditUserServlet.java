package io.muzoo.ooc.webapp.basic.servlets;

import io.muzoo.ooc.webapp.basic.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditUserServlet extends AbstractRoutableHttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(securityService.isAuthorized(request)) {
            String username = request.getParameter("username");
            User user = securityService.findByUserName(username);
            request.setAttribute("username", username);
            request.setAttribute("name", user.getName());
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/edituser.jsp");
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
        if(securityService.isAuthorized(request)) {
            String name = request.getParameter("name");
            if(name.isEmpty()) {
                error = "Name cannot be blank.";
            }

            if(error != null) {
                request.getSession().setAttribute("error", error);
            }
            else {
                try {
                    securityService.editUser(request);
                    response.sendRedirect("/");
                    return ;
                } catch (Exception e) {
                    request.getSession().setAttribute("error", e.getMessage());
                }
            }
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/edituser.jsp");
            requestDispatcher.include(request, response);
        }
        else {
            request.removeAttribute("error");
            response.sendRedirect("/login");
        }
    }

    @Override
    public String getPattern() {
        return "/user/edit";
    }
}
