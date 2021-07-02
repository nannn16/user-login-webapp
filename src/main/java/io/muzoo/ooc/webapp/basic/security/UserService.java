package io.muzoo.ooc.webapp.basic.security;


import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserService {

    private Map<String, User> users = new HashMap<>();
    {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/ssc_2020", "ssc", "secret"
            );
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from user");
            while(rs.next()){
                users.put(rs.getString("username"),
                        new User(rs.getString("username"), rs.getString("password")));
            }
            rs.close();
            stmt.close();
            con.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }


    public User findByUsername(String username) {
        return users.get(username);
    }

    public boolean checkIfUserExists(String username) {
        return users.containsKey(username);
    }

    public void addUser(String username, String password, String name) {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/ssc_2020", "ssc", "secret"
            );
            PreparedStatement stmt = con.prepareStatement("insert into user (username, password, name) value (?,?,?)");
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setString(3, name);
            stmt.executeUpdate();
            users.put(username, new User(username, password));
            stmt.close();
            con.close();
        }
        catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUser(String username) {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/ssc_2020", "ssc", "secret"
            );
            PreparedStatement stmt = con.prepareStatement("DELETE FROM user WHERE username=?");
            stmt.setString(1, username);
            stmt.executeUpdate();
            users.remove(username);
            stmt.close();
            con.close();
        }
        catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void editUser(String username, String name, int id) {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/ssc_2020", "ssc", "secret"
            );
            PreparedStatement stmt = con.prepareStatement("UPDATE user SET username = ? name = ? WHERE id=?");
            stmt.setString(1, name);
            stmt.setString(2, username);
            stmt.setInt(3, id);
            stmt.executeUpdate();
            stmt.close();
            con.close();
        }
        catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
