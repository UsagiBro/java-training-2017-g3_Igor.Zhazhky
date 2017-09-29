package ua.nure.zhazhky.SummaryTask4.model;

import java.io.Serializable;

public enum  Role implements Serializable {
    ADMIN, USER;

    public static Role getRoleById(int roleId) {
        return Role.values()[--roleId];
    }

    public String getName() { return name().toLowerCase(); }

    public int getId() {
        return ordinal() + 1;
    }
}
