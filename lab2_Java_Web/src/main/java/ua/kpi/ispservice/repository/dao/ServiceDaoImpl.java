package ua.kpi.ispservice.repository.dao;

import ua.kpi.ispservice.entity.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServiceDaoImpl extends BasicDao implements ServiceDao {

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

}
