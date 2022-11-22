package ua.kpi.ispservice.view.options;

public enum SortOption {
    ALPHABET_ASC("1. Alphabet ascending"),
    ALPHABET_DESC("2. Alphabet descending"),
    PRICE_ASC("3. Price ascending"),
    PRICE_DESC("4. Price descending");

    public final String label;

    private SortOption(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return label;
    }
}
