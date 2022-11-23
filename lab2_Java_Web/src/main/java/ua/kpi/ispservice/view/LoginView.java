package ua.kpi.ispservice.view;

import java.util.Scanner;

public class LoginView {
    private final Scanner scanner = new Scanner(System.in);

    public String getUsername() {
        System.out.println("Please, type in your username and press Enter: ");
        return scanner.nextLine();
    }

    public String getPassword() {
        System.out.println("Please, type in your password and press Enter: ");
        return scanner.nextLine();
    }

    public void unauthorizedMessage() {
        System.out.println("Incorrect credentials. Try again, please!\n");
    }
}
