package ua.kpi.ispservice.view.options;

public enum AdminOptions {

    ADD_TARIFF("1. Add new tariff"),
    DELETE_TARIFF("2. Delete existing tariff"),
    UPDATE_TARIFF("3. Update existing tariff"),
    REGISTER_CUSTOMER("4. Register new user"),
    BLOCK_CUSTOMER("5. Block user"),
    UNBLOCK_CUSTOMER("6. Unblock user");

    public final String label;

    private AdminOptions(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return label;
    }
}
