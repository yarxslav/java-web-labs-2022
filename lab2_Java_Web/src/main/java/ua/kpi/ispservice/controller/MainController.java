package ua.kpi.ispservice.controller;

import ua.kpi.ispservice.model.Model;
import ua.kpi.ispservice.view.GeneralMessages;
import ua.kpi.ispservice.view.View;

public class MainController {

    private final View view;

    public MainController(View view) {
        this.view = view;
    }

    public void execute() {
        view.displayMessage(GeneralMessages.GREETING.value);
        new LoginController(view, new Model()).execute();
    }
}
