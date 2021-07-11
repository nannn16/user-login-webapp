package io.muzoo.ooc.webapp.basic.security;

import io.muzoo.ooc.webapp.basic.model.User;
import lombok.Setter;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserService {

    private static final String INSERT_USER_SQL = "INSERT INTO user (username, password, name) VALUE (?,?,?)";
    private static final String DELETE_USER_SQL = "DELETE FROM user WHERE username=?";
    private static final String UPDATE_USER_SQL = "UPDATE user SET name = ? WHERE username=?";
    private static final String SELECT_USER_SQL = "SELECT * FROM user WHERE username = ?";
    private static final String SELECT_ALL_SQL = "SELECT * FROM user";
    private static final String CHANGE_PASSWORD_SQL = "UPDATE user SET password = ? WHERE username=?";
    @Setter
    private DatabaseConnectionService databaseConnectionService;

    public User findByUsername(String username) {
        try (Connection con = databaseConnectionService.getConnection();
             PreparedStatement stmt = con.prepareStatement(SELECT_USER_SQL)
        ) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            return new User(rs.getInt("id"),
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("name"));

        } catch (SQLException e) {
            return null;
        }
    }

    public boolean checkIfUserExists(String username) {
        try (Connection con = databaseConnectionService.getConnection();
             PreparedStatement stmt = con.prepareStatement(SELECT_USER_SQL)
        ) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void addUser(String username, String password, String name) throws UserServiceException {
        try (Connection con = databaseConnectionService.getConnection();
             PreparedStatement stmt = con.prepareStatement(INSERT_USER_SQL)
        ) {
            stmt.setString(1, username);
            stmt.setString(2, BCrypt.hashpw(password, BCrypt.gensalt()));
            stmt.setString(3, name);
            stmt.executeUpdate();
            con.commit();
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new UserNameNotUniqueException(String.format("username %s has already been taken.", username));
        } catch (SQLException e) {
            throw new UserServiceException(e.getMessage());
        }
    }

    public void removeUser(String username) {
        try (Connection con = databaseConnectionService.getConnection();
             PreparedStatement stmt = con.prepareStatement(DELETE_USER_SQL)
        ) {
            stmt.setString(1, username);
            stmt.executeUpdate();
            con.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editUser(String username, String name) throws UserServiceException {
        try (Connection con = databaseConnectionService.getConnection();
             PreparedStatement stmt = con.prepareStatement(UPDATE_USER_SQL)
        ) {
            stmt.setString(1, name);
            stmt.setString(2, username);
            stmt.executeUpdate();
            con.commit();
        } catch (SQLException e) {
            throw new UserServiceException(e.getMessage());
        }
    }

    public List<User> listUsers() {
        List<User> users = new ArrayList<>();
        try (Connection con = databaseConnectionService.getConnection();
             Statement stmt = con.createStatement()
        ) {
            ResultSet rs = stmt.executeQuery(SELECT_ALL_SQL);
            while (rs.next()) {

                HashMap<String, Object> map = new HashMap<>();
                map.put("id", rs.getInt("id"));
                map.put("username", rs.getString("username"));
                map.put("name", rs.getString("name"));
                users.add(new User(rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("name")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void changePassword(String username, String password) throws UserServiceException {
        try (Connection con = databaseConnectionService.getConnection();
             PreparedStatement stmt = con.prepareStatement(CHANGE_PASSWORD_SQL)
        ) {
            stmt.setString(1, BCrypt.hashpw(password, BCrypt.gensalt()));
            stmt.setString(2, username);
            stmt.executeUpdate();
            con.commit();
        } catch (SQLException e) {
            throw new UserServiceException(e.getMessage());
        }
    }
}
