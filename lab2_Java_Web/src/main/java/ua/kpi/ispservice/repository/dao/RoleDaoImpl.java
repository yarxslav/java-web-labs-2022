package ua.kpi.ispservice.repository.dao;

import ua.kpi.ispservice.entity.Role;

import java.sql.SQLException;

public class RoleDaoImpl extends BasicDao implements RoleDao {

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

}
