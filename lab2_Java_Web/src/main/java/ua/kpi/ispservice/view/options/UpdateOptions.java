package ua.kpi.ispservice.view.options;

public enum UpdateOptions {

    NAME("1. Tariff name"),
    DESCRIPTION("2. Tariff description"),
    COST("3. Tariff cost");

    public final String label;

    private UpdateOptions(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return label;
    }

}
