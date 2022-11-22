package ua.kpi.ispservice.repository.dao;

import ua.kpi.ispservice.repository.utils.ConnectionFactory;
import ua.kpi.ispservice.entity.Service;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServiceDaoImpl implements ServiceDao {

    Connection connection = null;
    PreparedStatement ptmt = null;
    ResultSet resultSet = null;

    private Connection getConnection() throws SQLException {
        Connection conn;
        conn = ConnectionFactory.getInstance().getConnection();
        return conn;
    }

    @Override
    public List<Service> findAll() {
        List<Service> services = new ArrayList<>();

        try {
            String queryString = "SELECT * FROM service";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            resultSet = ptmt.executeQuery();
            while (resultSet.next()) {
                services.add(new Service(resultSet.getLong("id"), resultSet.getString("service_name"),
                        resultSet.getString("description")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }

        return services;
    }

    @Override
    public Service findServiceById(Long id) {
        Service service = null;

        try {
            String queryString = "SELECT * FROM service WHERE id=?";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.setLong(1, id);
            resultSet = ptmt.executeQuery();
            while (resultSet.next()) {
                service = new Service(resultSet.getLong("id"), resultSet.getString("name"),
                        resultSet.getString("description"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }

        return service;
    }

    @Override
    public Service findServiceByName(String name) {
        Service service = null;

        try {
            String queryString = "SELECT * FROM service WHERE service_name=?";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.setString(1, name);
            resultSet = ptmt.executeQuery();
            while (resultSet.next()) {
                service = new Service(resultSet.getLong("id"), resultSet.getString("service_name"),
                        resultSet.getString("description"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }

        return service;
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
