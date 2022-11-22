package ua.kpi.ispservice.repository.dao;

import ua.kpi.ispservice.entity.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl extends BasicDao implements UserDao {

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
        } catch (SQLException e) {
            System.out.println("Ooops...Something went wrong. Unable to create new user :(");
        } finally {
            closeResources();
        }
    }

    @Override
    public void updateStatus(User user, boolean isBlocked) {
        try {
            String queryString = "UPDATE \"user\" SET blocked=? WHERE id=?";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.setBoolean(1, isBlocked);
            ptmt.setLong(2, user.getId());
            ptmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Ooops...Something went wrong. Unable to update user's status :(");
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
                role = resultSet.getString("role_name");
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
            System.out.println("Ooops...Something went wrong. Unable to find user :(");
        } finally {
            closeResources();
        }

        return user;
    }

}
