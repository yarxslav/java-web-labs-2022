package ua.kpi.ispservice.dao;

import ua.kpi.ispservice.entity.Account;
import ua.kpi.ispservice.utils.ConnectionFactory;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountDaoImpl implements AccountDao {
    Connection connection = null;
    PreparedStatement ptmt = null;
    ResultSet resultSet = null;

    private Connection getConnection() throws SQLException {
        Connection conn;
        conn = ConnectionFactory.getInstance().getConnection();
        return conn;
    }

    @Override
    public void create(Long ownerId) {
        try {
            String queryString = "INSERT INTO account(owner_id, balance) VALUES (?, ?)";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.setLong(1, ownerId);
            ptmt.setDouble(2, 0.0);
            ptmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
    }

    @Override
    public void updateBalance(Long ownerId, BigDecimal amount) {
        try {
            String queryString = "UPDATE account SET balance=? WHERE owner_id=?";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.setDouble(1, amount.doubleValue());
            ptmt.setLong(2, ownerId);
            ptmt.executeUpdate();
            System.out.println("Account balance updated successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
    }

    @Override
    public List<Account> findAll() {
        List<Account> accounts = new ArrayList<>();

        try {
            String queryString = "SELECT * FROM account";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            resultSet = ptmt.executeQuery();
            while (resultSet.next()) {
                accounts.add(new Account(resultSet.getLong("id"), resultSet.getLong("owner_id"),
                        new BigDecimal(resultSet.getDouble("balance"))));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }

        return accounts;
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
