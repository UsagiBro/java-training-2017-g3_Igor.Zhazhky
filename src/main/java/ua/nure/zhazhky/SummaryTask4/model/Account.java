package ua.nure.zhazhky.SummaryTask4.model;

import java.io.Serializable;

public class Account implements Serializable {

    private String accountId;
    private int balance;
    private String name;
    private boolean blocked;
    private User user;

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "AccountDAO{" +
                "accountId=" + accountId +
                ", balance=" + balance +
                ", blocked=" + blocked +
                ", user=" + user +
                ", name='" + name + '\'' +
                '}';
    }
}
