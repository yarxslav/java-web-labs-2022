package ua.kpi.ispservice;

import ua.kpi.ispservice.controller.LoginController;
import ua.kpi.ispservice.controller.MainController;
import ua.kpi.ispservice.model.Model;
import ua.kpi.ispservice.view.View;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {
        MainController mainController = new MainController(new View());

        try {
            mainController.execute();
        } catch (IllegalArgumentException e) {
            mainController.execute();
        }

    }
}
