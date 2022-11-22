package ua.kpi.ispservice.controller;

public enum DownloadOptions {

    TXT("1. TXT"),
    PDF("2. PDF"),
    DOCX("3. DOCX");

    public final String label;

    private DownloadOptions(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return label;
    }
}
