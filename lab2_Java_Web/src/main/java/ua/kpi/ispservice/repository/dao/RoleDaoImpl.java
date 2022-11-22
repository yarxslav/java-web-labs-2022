package ua.kpi.ispservice.repository.dao;

import ua.kpi.ispservice.entity.Role;
import ua.kpi.ispservice.repository.utils.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleDaoImpl implements RoleDao {

    Connection connection = null;
    PreparedStatement ptmt = null;
    ResultSet resultSet = null;

    private Connection getConnection() throws SQLException {
        Connection conn;
        conn = ConnectionFactory.getInstance().getConnection();
        return conn;
    }

    @Override
    public Role getRoleByName(String name) {
        Role role = null;

        try {
            String queryString = "SELECT * FROM role WHERE role_name=?";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.setString(1, name);
            resultSet = ptmt.executeQuery();
            while (resultSet.next()) {
                role = new Role(resultSet.getLong("id"), resultSet.getString("role_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }

        return role;
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
