package ua.kpi.ispservice.controller;

public enum CustomerOptions {

    CHECK_BALANCE("1. Check balance"),
    UPDATE_BALANCE("2. Update balance"),
    CHECK_SERVICES_LIST("3. Check services list"),
    CHECK_TARIFFS_FOR_SERVICE("4. Check tariffs for exact service"),
    DOWNLOAD_ALL_TARIFFS("5. Download all tariffs"),
    DOWNLOAD_TARIFFS("6. Download tariffs for exact service"),
    SUBSCRIBE("7. Subscribe"),
    CHECK_SUBSCRIPTION("8. Check subscriptions"),
    LOG_OUT("9. Log out");

    public final String label;

    private CustomerOptions(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return label;
    }

}
