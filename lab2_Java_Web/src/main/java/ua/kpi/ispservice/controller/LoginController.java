package ua.kpi.ispservice.controller;

import ua.kpi.ispservice.service.LoginService;
import ua.kpi.ispservice.view.LoginView;

public class LoginController {

    private LoginView loginView;
    private LoginService loginService;

    public LoginController(LoginView loginView, LoginService loginService) {
        this.loginView = loginView;
        this.loginService = loginService;
    }

    public boolean login() {
        String username = loginView.getUsername();
        String password = loginView.getPassword();
        return loginService.login(username, password);
    }
}
