package ua.kpi.ispservice.view;

public enum AdminOptionsMessages {

    CREATE_TARIFF_OPTION("1. Create new tariff"),
    DELETE_TARIFF_OPTION("2. Delete existed tariff"),
    UPDATE_TARIFF_OPTION("3. Update existed tariff options"),
    CREATE_USER_OPTION("4. Register new user"),
    BLOCK_USER_OPTION("5. Block existed user"),
    UNBLOCK_USER_OPTION("6. Block existed user");

    public final String value;

    private AdminOptionsMessages(String value) {
        this.value = value;
    }
}
