package ua.kpi.ispservice.view;

import ua.kpi.ispservice.view.options.AdminOptions;
import ua.kpi.ispservice.view.options.UpdateOptions;

import java.util.Scanner;

public class AdminView {
    private final Scanner scanner = new Scanner(System.in);

    public void greeting(String username) {
        System.out.println("Glad to see you, " + username + "!\n");
    }

    public AdminOptions defineAdminOption() {
        AdminOptions option = null;

        System.out.println("Select an option: (Type in the number of option)");
        displayOptions();

        String value = scanner.nextLine();

        for (AdminOptions adminOption : AdminOptions.values()) {
            if (value.equals(adminOption.toString().substring(0, 1))) {
                option = adminOption;
            }
        }

        return option;
    }

    private void displayOptions() {
        for (AdminOptions adminOption : AdminOptions.values()) {
            System.out.println("\t" + adminOption.toString());
        }
    }

    public String askForData(String type) {
        System.out.println("Please, type in " + type + " and press Enter");
        String data = scanner.nextLine();
        return data;
    }

    public UpdateOptions defineUpdateOption() {
        UpdateOptions option = null;

        System.out.println("Select what you want to change: (Type in the number of option)");
        for (UpdateOptions updateOption : UpdateOptions.values()) {
            System.out.println("\t" + updateOption.toString());
        }

        String value = scanner.nextLine();

        for (UpdateOptions updateOption : UpdateOptions.values()) {
            if (value.equals(updateOption.toString().substring(0, 1))) {
                option = updateOption;
            }
        }

        return option;
    }

    public void userAlreadyExisting() {
        System.out.println("User with such username already exists. Choose another username!\n");
    }

    public void userDoesntExist() {
        System.out.println("User with such username doesn't exist. Try again!\n");
    }

    public void tariffAdded(String name) {
        System.out.println("New tariff " + name + " added successfully!\n");
    }

    public void tariffUpdated() {
        System.out.println("Tariff updated successfully\n");
    }

    public void tariffDeleted() {
        System.out.println("Tariff deleted successfully\n");
    }

    public void userAdded(String username) {
        System.out.println("User " + username + " added successfully\n");
    }

    public void statusUpdated(boolean isBlocked) {
        if (isBlocked) {
            System.out.println("User blocked successfully\n");
        } else {
            System.out.println("User unblocked successfully\n");
        }
    }
}
