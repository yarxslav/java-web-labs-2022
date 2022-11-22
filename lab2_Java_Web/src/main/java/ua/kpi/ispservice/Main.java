package ua.kpi.ispservice;

import ua.kpi.ispservice.controller.CustomerOptions;
import ua.kpi.ispservice.controller.IndexController;
import ua.kpi.ispservice.repository.UserRepository;
import ua.kpi.ispservice.repository.dao.UserDaoImpl;
import ua.kpi.ispservice.view.IndexView;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {
//        for (CustomerOptions customerOption : CustomerOptions.values()) {
//            System.out.println(customerOption.toString());
//        }
        new IndexController(new IndexView(), new UserRepository(new UserDaoImpl())).execute();
    }
}
