package ua.kpi.ispservice;

import ua.kpi.ispservice.dao.UserJDBCDAO;
import ua.kpi.ispservice.entity.User;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {
        UserJDBCDAO userJDBCDAO = new UserJDBCDAO();

        User user = new User();
        user.setId(5L);
        user.setUsername("tester");
        user.setPassword("test");
        user.setRoleId(1L);

        userJDBCDAO.findAll();
    }
}
