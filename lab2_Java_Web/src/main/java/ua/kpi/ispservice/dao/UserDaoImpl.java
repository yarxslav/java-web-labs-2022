package ua.kpi.ispservice.dao;

import ua.kpi.ispservice.entity.User;
import ua.kpi.ispservice.utils.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    Connection connection = null;
    PreparedStatement ptmt = null;
    ResultSet resultSet = null;

    public UserDaoImpl() {

    }

    private Connection getConnection() throws SQLException {
        Connection conn;
        conn = ConnectionFactory.getInstance().getConnection();
        return conn;
    }

    public List<User> findAll() {
        List<User> users = new ArrayList<>();

        try {
            String queryString = "SELECT * FROM \"user\"";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            resultSet = ptmt.executeQuery();
            while (resultSet.next()) {
                users.add(new User(resultSet.getLong("id"), resultSet.getString("username"),
                        resultSet.getString("password"), resultSet.getLong("role_id"), resultSet.getBoolean("blocked")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }

        return users;
    }

    @Override
    public void create(User user) {
        try {
            String queryString = "INSERT INTO \"user\"(username, password, role_id, blocked) VALUES(?, ?, ?, ?)";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.setString(1, user.getUsername());
            ptmt.setString(2, user.getPassword());
            ptmt.setLong(3, user.getRoleId());
            ptmt.setBoolean(4, user.isBlocked());
            ptmt.executeUpdate();
            System.out.println("User " + user.getUsername() + " Added Successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
    }

    @Override
    public void block(User user) {
        try {
            String queryString = "UPDATE \"user\" SET blocked=? WHERE id=?";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.setBoolean(1, user.isBlocked());
            ptmt.setLong(2, user.getId());
            ptmt.executeUpdate();
            System.out.println("User " + user.getUsername() + " blocked successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
    }

    @Override
    public void unblock(User user) {
        try {
            String queryString = "UPDATE \"user\" SET blocked=? WHERE id=?";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.setBoolean(1, user.isBlocked());
            ptmt.setLong(2, user.getId());
            ptmt.executeUpdate();
            System.out.println("User " + user.getUsername() + " unblocked successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
    }

    @Override
    public String role(User user) {
        String role = "Customer";

        try {
            String queryString = "SELECT r.role_name FROM \"user\" " +
                    "JOIN role AS r ON \"user\".role_id = r.id " +
                    "WHERE \"user\".id = ?";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.setLong(1, user.getId());
            resultSet = ptmt.executeQuery();
            while (resultSet.next()) {
                role = resultSet.getString("r.role_name");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }

        return role;
    }

    @Override
    public User findUserByUsername(String username) {
        User user = null;

        try {
            String queryString = "SELECT * FROM \"user\" WHERE username = ?";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.setString(1, username);
            resultSet = ptmt.executeQuery();
            while (resultSet.next()) {
                user = new User(resultSet.getLong("id"), resultSet.getString("username"),
                        resultSet.getString("password"), resultSet.getLong("role_id"),
                        resultSet.getBoolean("blocked"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }

        return user;
    }

    public void closeResources() {
        try {
            if (resultSet != null)
                resultSet.close();
            if (ptmt != null)
                ptmt.close();
            if (connection != null)
                connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
