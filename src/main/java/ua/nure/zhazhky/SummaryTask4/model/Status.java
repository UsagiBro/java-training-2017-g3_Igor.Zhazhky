package ua.nure.zhazhky.SummaryTask4.model;

public enum Status {
    PREPARED, SENT;

    public static Status getStateById(int roleId) {
        return Status.values()[--roleId];
    }

    public String getName() {
        return name().toLowerCase();
    }

    public int getId() {
        return ordinal() + 1;
    }
}
