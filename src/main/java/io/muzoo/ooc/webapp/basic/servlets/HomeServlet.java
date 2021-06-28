package io.muzoo.ooc.webapp.basic.servlets;

import io.muzoo.ooc.webapp.basic.security.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomeServlet extends AbstractRoutableHttpServlet {

    protected void makeTable(HttpServletRequest request) {
        List<Map> list = new ArrayList<Map>();
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/ssc_2020", "ssc", "secret"
            );
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from user");
            while(rs.next()){
                HashMap<String, Object> map = new HashMap<>();
                map.put("id", rs.getInt("id"));
                map.put("username", rs.getString("username"));
                map.put("name", rs.getString("name"));
                list.add(map);
            }
            con.close();
            request.setAttribute("list", list);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (securityService.isAuthorized(request)) {
            String username = securityService.getCurrentUsername(request);
            request.setAttribute("username", username);
            makeTable(request);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/home.jsp");
            requestDispatcher.include(request, response);
        } else {
            response.sendRedirect("/login");
        }
    }

    @Override
    public String getPattern() {
        return "/index.jsp";
    }
}
