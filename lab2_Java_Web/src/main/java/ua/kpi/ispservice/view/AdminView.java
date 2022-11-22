package ua.kpi.ispservice.view;

import ua.kpi.ispservice.controller.AdminOptions;
import ua.kpi.ispservice.controller.UpdateOptions;

import java.util.Scanner;

public class AdminView {
    private final Scanner scanner = new Scanner(System.in);

    public void greeting(String username) {
        System.out.println("Glad to see you, " + username + "!");
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
            System.out.println(adminOption.toString());
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
            System.out.println(updateOption.toString());
        }

        String value = scanner.nextLine();

        for (UpdateOptions updateOption : UpdateOptions.values()) {
            if (value.equals(updateOption.toString().substring(0, 1))) {
                option = updateOption;
            }
        }

        return option;
    }
}
