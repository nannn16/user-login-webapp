package io.muzoo.ooc.webapp.basic.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Objects;

public class SecurityService {

    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public String getCurrentUsername(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Object usernameObject = session.getAttribute("username");
        return (String) usernameObject;
    }

    public boolean isAuthorized(HttpServletRequest request) {
        String username = getCurrentUsername(request);
        return userService.checkIfUserExists(username);
    }

    public void logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute("username");
        session.invalidate();
    }

    public boolean login(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = userService.findByUsername(username);
        if (user != null && Objects.equals(user.getPassword(), password)) {
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            return true;
        } else {
            return false;
        }
    }

    public boolean addUser(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        if (!userService.checkIfUserExists(username)) {
            userService.addUser(username, password, name);
            return true;
        }
        else {
            return false;
        }
    }

    public void removeUser(HttpServletRequest request) {
        String username = request.getParameter("removeuser");
        if(userService.checkIfUserExists(username)) {
            userService.removeUser(username);
        }
    }

}
