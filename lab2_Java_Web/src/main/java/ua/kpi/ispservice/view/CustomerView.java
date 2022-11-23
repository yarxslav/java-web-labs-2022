package ua.kpi.ispservice.view;

import ua.kpi.ispservice.view.options.CustomerOptions;
import ua.kpi.ispservice.view.options.DownloadOptions;
import ua.kpi.ispservice.entity.Service;
import ua.kpi.ispservice.entity.Subscription;
import ua.kpi.ispservice.entity.Tariff;
import ua.kpi.ispservice.view.options.SortOption;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class CustomerView {
    Scanner scanner = new Scanner(System.in);

    public CustomerOptions defineCustomerOption() {
        CustomerOptions option = null;

        System.out.println("Select an option: (Type in the number of option)");
        displayOptions();

        String value = scanner.nextLine();

        for (CustomerOptions customerOption : CustomerOptions.values()) {
            if (value.equals(customerOption.toString().substring(0, 1))) {
                option = customerOption;
            }
        }

        return option;
    }

    public void displayBalance(BigDecimal balance) {
        System.out.println("Your current balance is: " + balance.toString() + "\n");
    }

    public BigDecimal updateBalanceDialog() {
        System.out.println("Enter your card number: ");
        String cardNumber = scanner.next();

        System.out.println("Type in an amount of funds you want to add to your balance (In format \"XX,XX\"): ");
        BigDecimal amount = new BigDecimal(scanner.nextDouble());

        return amount;
    }

    public SortOption defineSortOption() {
        SortOption option = null;

        System.out.println("Select an option how you want to sort tariffs: (Type in the number of option)");
        for (SortOption sortOption : SortOption.values()) {
            System.out.println("\t" + sortOption.toString());
        }

        String value = scanner.nextLine();

        for (SortOption sortOption : SortOption.values()) {
            if (value.equals(sortOption.toString().substring(0, 1))) {
                option = sortOption;
            }
        }

        return option;
    }

    private void displayOptions() {

        for (CustomerOptions customerOption : CustomerOptions.values()) {
            System.out.println("\t" + customerOption.toString());
        }

    }


    public void displayServicesList(List<Service> services) {

        if (!services.isEmpty() || !(services == null)) {
            services.forEach(System.out::println);
            System.out.println("\n");
        } else {
            System.out.println("Ooops... Seems like there are no services yet.\n");
        }
    }

    public String getServiceName() {
        System.out.println("Enter the name of service: ");
        String serviceName = scanner.nextLine();
        return serviceName;
    }

    public void displayTariffs(List<Tariff> tariffs) {
        if (!tariffs.isEmpty() || !(tariffs == null)) {
            tariffs.forEach(System.out::println);
        } else {
            System.out.println("Ooops... Seems like there are no available tariffs for this service.");
        }
    }

    public DownloadOptions defineDownloadOptions() {
        DownloadOptions option = null;

        System.out.println("Select the format you want to download file: (Type in the number of option)");
        for (DownloadOptions downloadOption : DownloadOptions.values()) {
            System.out.println("\t" + downloadOption.toString());
        }

        String value = scanner.nextLine();

        for (DownloadOptions downloadOptions : DownloadOptions.values()) {
            if (value.equals(downloadOptions.toString().substring(0, 1))) {
                option = downloadOptions;
            }
        }

        return option;
    }

    public String getTariffName() {
        System.out.println("Enter the name of tariff: ");
        String serviceName = scanner.nextLine();
        return serviceName;
    }

    public void blockedMessage() {
        System.out.println("Your account was blocked due to lack of funds.");
        System.out.println("To be able to subscribe, please, top up your balance.\n");
    }

    public void greeting(String username) {
        System.out.println("Glad to see you, " + username + "!\n");
    }

    public void blockNotification(boolean blocked) {
        if (blocked) {
            System.out.println("Your account is BLOCKED at this moment.");
            System.out.println("Please, top up your balance.\n");
        }
    }

    public void displaySubscriptions(List<Subscription> subscriptions) {
        System.out.println("You subscribed to: ");
        subscriptions.forEach(System.out::println);
        System.out.println("\n");
    }

    public void balanceUpdated() {
        System.out.println("Account balance updated successfully!\n");
    }

    public void downloadSuccess() {
        System.out.println("Successfully downloaded. Look for file in Downloads folder.\n");
    }

    public void wrongOption() {
        System.out.println("Ooops... Seems like you didn't type the right option in. Try again!\n");
    }

    public void wrongServiceName() {
        System.out.println("Ooops...Seems like there's no service with such title. Try again!\n");
    }

    public void wrongSortOption() {
        System.out.println("Ooops...Seems like there's no such sort option. Try again!\n");
    }

    public void downloadGroupedTariffs() {
        System.out.println("If you want to download tariffs grouped by service, please, type in \'Group\' and press Enter\n");
    }

    public void subscriptionExists() {
        System.out.println("You have this subscription already! Do you have a lot of funds?\n");
    }

    public void subscribedSuccessfuly() {
        System.out.println("Subscription added successfully\n");
    }

    public void noSubs() {
        System.out.println("Seems like you don't have subscriptions yet :(\n");
    }

    public void wrongDownloadOption() {
        System.out.println("There's no such download option. Try again!\n");
    }
}
