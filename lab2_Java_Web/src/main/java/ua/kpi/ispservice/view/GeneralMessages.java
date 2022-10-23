package ua.kpi.ispservice.view;

public enum GeneralMessages {
    GREETING("Welcome to ISP application! Please, log in."),
    REQUEST_USERNAME("Enter username and press Enter: "),
    REQUEST_PASSWORD("Enter password and press Enter: "),
    GREETING_USER("Hello, "),
    REQUEST_ACTION("Choose below what you want to do (Type the number of option and press Enter): ");

    public final String value;

    private GeneralMessages(String value) {
        this.value = value;
    }
}
