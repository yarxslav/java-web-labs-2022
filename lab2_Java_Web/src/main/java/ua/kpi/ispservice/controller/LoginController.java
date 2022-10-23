package ua.kpi.ispservice.controller;

import ua.kpi.ispservice.entity.User;
import ua.kpi.ispservice.model.Model;
import ua.kpi.ispservice.view.ErrorMessages;
import ua.kpi.ispservice.view.GeneralMessages;
import ua.kpi.ispservice.view.View;

import java.util.Scanner;

public class LoginController {

    private final View view;
    private final Model model;
    private final Scanner scanner;
    private User currentUser;

    public LoginController(View view, Model model) {
        this.view = view;
        this.model = model;
        this.scanner = new Scanner(System.in);
    }

    public void execute() {
        login();
    }

    private void login() {
        view.displayMessage(GeneralMessages.REQUEST_USERNAME.value);
        String username = scanner.nextLine();

        view.displayMessage(GeneralMessages.REQUEST_PASSWORD.value);
        String password = scanner.nextLine();
        try {
            if (userExists(username)) {
                currentUser = model.getUserByUsername(username);
                if (correctPassword(currentUser, password)) {
                    view.displayMessage(GeneralMessages.GREETING_USER.value + currentUser.getUsername() + "!");
                } else {
                    currentUser = null;
                    view.displayMessage(ErrorMessages.PASSWORD_NOT_CORRECT.value);
                    throw new IllegalArgumentException();
                }
            } else {
                view.displayMessage(ErrorMessages.USER_NOT_EXISTS_ERROR.value);
                throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException e) {
            throw e;
        }
    }

    private boolean userExists(String username) {
        return model.userExists(username);
    }

    private boolean correctPassword(User user, String password) {
        return model.correctPassword(user, password);
    }

}
