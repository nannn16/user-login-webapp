package io.muzoo.ooc.webapp.basic.security;

import io.muzoo.ooc.webapp.basic.model.User;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

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
        if (user != null && BCrypt.checkpw(password, user.getPassword())) {
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            return true;
        } else {
            return false;
        }
    }

    public boolean addUser(HttpServletRequest request) throws UserServiceException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        if (!password.equals(confirmPassword)) {
            return false;
        } else {
            String name = request.getParameter("name");
            userService.addUser(username, password, name);
        }
        return true;
    }

    public boolean checkIfUserExists(String username) {
        return userService.checkIfUserExists(username);
    }

    public User findByUserName(String username) {
        return userService.findByUsername(username);
    }

    public void removeUser(HttpServletRequest request) {
        String username = request.getParameter("removeuser");
        String currentUser = getCurrentUsername(request);
        if (userService.checkIfUserExists(username) && !username.equals(currentUser)) {
            userService.removeUser(username);
        }
    }

    public void editUser(HttpServletRequest request) throws UserServiceException {
        String username = request.getParameter("username");
        String name = request.getParameter("name");
        if (userService.checkIfUserExists(username)) {
            userService.editUser(username, name);
        }
    }

    public List<User> listUsers() {
        List<User> users = userService.listUsers();
        return users;
    }

    public void changePassword(HttpServletRequest request) throws UserServiceException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        userService.changePassword(username, password);
    }

}
