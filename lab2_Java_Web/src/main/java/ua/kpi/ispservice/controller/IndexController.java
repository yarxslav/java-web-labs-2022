package ua.kpi.ispservice.controller;

import ua.kpi.ispservice.context.ApplicationContext;
import ua.kpi.ispservice.repository.UserRepository;
import ua.kpi.ispservice.service.LoginService;
import ua.kpi.ispservice.view.AdminView;
import ua.kpi.ispservice.view.CustomerView;
import ua.kpi.ispservice.view.IndexView;
import ua.kpi.ispservice.view.LoginView;

public class IndexController {

    private final IndexView indexView;
    private final UserRepository userRepository;
    LoginService loginService;

    public IndexController(IndexView indexView, UserRepository userRepository) {
        this.indexView = indexView;
        this.userRepository = userRepository;
        loginService = new LoginService(userRepository);
    }

    public void execute() {
        login();

        do {
            switch (loginService.defineUserRole(ApplicationContext.getInstance().getCurrentUser())) {
                case "Customer" -> executeCustomer();
                case "Administrator" -> executeAdmin();
                case "NO_ROLE" -> indexView.somethingWentWrong();
            }
        } while (indexView.goToMenu());
    }

    private void login() {
        if (!ApplicationContext.getInstance().isLoggedIn()) {
            LoginView loginView = new LoginView();
            LoginController loginController = new LoginController(loginView, loginService);

            do {
                if (!loginController.login()) {
                    loginView.unauthorizedMessage();
                }
            } while (!ApplicationContext.getInstance().isLoggedIn());
        }
    }

    private void executeCustomer() {
        CustomerController customerController = new CustomerController(new CustomerView());
        customerController.execute();
    }

    private void executeAdmin() {
        AdminController adminController = new AdminController(new AdminView());
        adminController.execute();
    }
}