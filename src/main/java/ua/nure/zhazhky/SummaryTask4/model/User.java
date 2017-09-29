package ua.nure.zhazhky.SummaryTask4.model;

import java.io.Serializable;

public class User implements Serializable {
    private String login;
    private String password;
    private String fullName;
    private Role role;
    private boolean blocked;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullname) {
        this.fullName = fullname;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", fullName='" + fullName + '\'' +
                ", role=" + role +
                ", blocked=" + blocked +
                '}';
    }

    public boolean isFilled() {
        return this.login != null &&
                this.password != null &&
                this.fullName != null &&
                this.role != null;
    }
}
