package ua.kpi.ispservice.repository.dao;

import ua.kpi.ispservice.entity.Subscription;
import ua.kpi.ispservice.repository.utils.ConnectionFactory;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SubscriptionDaoImpl implements SubscriptionDao {

    Connection connection = null;
    PreparedStatement ptmt = null;
    ResultSet resultSet = null;

    private Connection getConnection() throws SQLException {
        Connection conn;
        conn = ConnectionFactory.getInstance().getConnection();
        return conn;
    }

    @Override
    public void create(Subscription subscription) {
        try {
            String queryString = "INSERT INTO subscription(customer_id, service_id, tariff_id) VALUES (?, ?, ?)";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.setLong(1, subscription.getUserId());
            ptmt.setLong(2, subscription.getServiceId());
            ptmt.setLong(3, subscription.getTariffId());
            ptmt.executeUpdate();
            System.out.println("Subscription Added Successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
    }

    @Override
    public List<Subscription> findAll() {
        List<Subscription> subscriptions = new ArrayList<>();

        try {
            String queryString = "SELECT * FROM subscription";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            resultSet = ptmt.executeQuery();
            while (resultSet.next()) {
                subscriptions.add(new Subscription(resultSet.getLong("id"),
                        resultSet.getLong("customer_id"),
                        resultSet.getLong("service_id"),
                        resultSet.getLong("tariff_id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }

        return subscriptions;
    }

    @Override
    public List<Subscription> findByCustomerId(Long id) {
        List<Subscription> subscriptions = new ArrayList<>();

        try {
            String queryString = "select sub.id, sub.customer_id, sub.service_id, sub.tariff_id, " +
                    "s.service_name, t.name, t.cost from subscription as sub\n" +
                    "    join service s on s.id = sub.service_id\n" +
                    "join tariff t on t.id = sub.tariff_id where sub.customer_id=?;";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.setLong(1, id);
            resultSet = ptmt.executeQuery();
            while (resultSet.next()) {
                subscriptions.add(new Subscription(resultSet.getLong("id"),
                        resultSet.getLong("customer_id"),
                        resultSet.getLong("service_id"),
                        resultSet.getString("service_name"),
                        resultSet.getString("name"),
                        new BigDecimal(resultSet.getDouble("cost")),
                        resultSet.getLong("tariff_id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }

        return subscriptions;
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