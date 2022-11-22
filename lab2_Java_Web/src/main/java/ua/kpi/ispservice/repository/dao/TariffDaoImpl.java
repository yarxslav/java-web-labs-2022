package ua.kpi.ispservice.repository.dao;

import ua.kpi.ispservice.entity.Tariff;
import ua.kpi.ispservice.repository.utils.ConnectionFactory;
import ua.kpi.ispservice.repository.utils.SortOption;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TariffDaoImpl implements TariffDao {

    Connection connection = null;
    PreparedStatement ptmt = null;
    ResultSet resultSet = null;

    private Connection getConnection() throws SQLException {
        Connection conn;
        conn = ConnectionFactory.getInstance().getConnection();
        return conn;
    }

    @Override
    public void add(Tariff tariff) {
        try {
            String queryString = "INSERT INTO tariff(service_id, name, description, cost) VALUES (?, ?, ?, ?)";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.setLong(1, tariff.getServiceId());
            ptmt.setString(2, tariff.getName());
            ptmt.setString(3, tariff.getDescription());
            ptmt.setDouble(4, tariff.getCost().doubleValue());
            ptmt.executeUpdate();
            System.out.println("Tariff" + tariff.getName() + " added successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
    }

    @Override
    public void update(Tariff tariff) {
        try {
            String queryString = "UPDATE tariff SET name=?, description=?, cost=? WHERE id=?";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.setString(1, tariff.getName());
            ptmt.setString(2, tariff.getDescription());
            ptmt.setDouble(3, tariff.getCost().doubleValue());
            ptmt.setLong(4, tariff.getId());
            ptmt.executeUpdate();
            System.out.println("Tariff updated successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
    }

    @Override
    public void delete(Tariff tariff) {
        try {
            String queryString = "DELETE FROM tariff WHERE id=?";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.setLong(1, tariff.getId());
            ptmt.executeUpdate();
            System.out.println("Tariff deleted successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
    }

    @Override
    public List<Tariff> findAll(SortOption sortOption) {
        List<Tariff> tariffs = new ArrayList<>();

        try {
            String queryString = switch (sortOption) {
                case ALPHABET_ASC -> "SELECT * FROM tariff ORDER BY name ASC";
                case ALPHABET_DESC -> "SELECT * FROM tariff ORDER BY name DESC";
                case PRICE -> "SELECT * FROM tariff ORDER BY price ASC";
            };

            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            resultSet = ptmt.executeQuery();
            while (resultSet.next()) {
                tariffs.add(new Tariff(resultSet.getLong("id"), resultSet.getLong("service_id"),
                        resultSet.getString("name"), resultSet.getString("description"),
                        new BigDecimal(resultSet.getDouble("cost"))));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }

        return tariffs;
    }

    @Override
    public Tariff findById(Long id) {
        Tariff tariff = null;

        try {
            String queryString = "SELECT * FROM tariff WHERE id=?";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.setLong(1, id);
            resultSet = ptmt.executeQuery();
            tariff = new Tariff(resultSet.getLong("id"), resultSet.getLong("service_id"),
                    resultSet.getString("name"), resultSet.getString("description"),
                    new BigDecimal(resultSet.getDouble("cost")));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }

        return tariff;
    }

    @Override
    public Tariff findByIdAndServiceId(Long id, Long serviceId) {
        Tariff tariff = null;

        try {
            String queryString = "SELECT * FROM tariff WHERE id=? AND service_id=?";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.setLong(1, id);
            ptmt.setLong(2, serviceId);
            resultSet = ptmt.executeQuery();
            tariff = new Tariff(resultSet.getLong("id"), resultSet.getLong("service_id"),
                    resultSet.getString("name"), resultSet.getString("description"),
                    new BigDecimal(resultSet.getDouble("cost")));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }

        return tariff;
    }

    @Override
    public List<Tariff> findByServiceId(Long serviceId) {
        List<Tariff> tariffs = new ArrayList<>();

        try {
            String queryString = "SELECT * FROM tariff WHERE service_id=?";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.setLong(1, serviceId);
            resultSet = ptmt.executeQuery();

            while (resultSet.next()) {
                tariffs.add(new Tariff(resultSet.getLong("id"), resultSet.getLong("service_id"),
                        resultSet.getString("name"), resultSet.getString("description"),
                        new BigDecimal(resultSet.getDouble("cost"))));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }

        return tariffs;
    }

    public Tariff findByNameAndServiceId(String name, Long serviceId) {
        Tariff tariff = null;

        try {
            String queryString = "SELECT * FROM tariff WHERE name=? AND service_id=?";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.setString(1, name);
            ptmt.setLong(2, serviceId);
            resultSet = ptmt.executeQuery();
            if(resultSet.next()) {
                tariff = new Tariff(resultSet.getLong("id"), resultSet.getLong("service_id"),
                        resultSet.getString("name"), resultSet.getString("description"),
                        new BigDecimal(resultSet.getDouble("cost")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }

        return tariff;
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
