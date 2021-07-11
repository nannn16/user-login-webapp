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
            String username = securityService.getCurrentUsername(request);
            String removeUser = request.getParameter("removeUser");
            request.setAttribute("removeUser", removeUser);
            if(username.equals(removeUser)) {
                String error = "Cannot remove their own account.";
                request.getSession().setAttribute("error", error);
                response.sendRedirect("/");
                return ;
            }
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/confirm.jsp");
            requestDispatcher.include(request, response);
        } else {
            request.removeAttribute("error");
            response.sendRedirect("/login");
        }
    }

    @Override
    public String getPattern() {
        return "/user/confirmremove";
    }
}
