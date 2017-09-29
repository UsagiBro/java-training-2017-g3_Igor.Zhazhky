package ua.nure.zhazhky.SummaryTask4.model;

import java.io.Serializable;

public class Payment implements Serializable {

    private String date;
    private String status;
    private String accountSender;
    private String accountReceiver;
    private int balance;
    private String userLogin;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAccountSender() {
        return accountSender;
    }

    public void setAccountSender(String accountSender) {
        this.accountSender = accountSender;
    }

    public String getAccountReceiver() {
        return accountReceiver;
    }

    public void setAccountReceiver(String accountReceiver) {
        this.accountReceiver = accountReceiver;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    @Override
    public String toString() {
        return "Payment{" +
                ", date='" + date + '\'' +
                ", status=" + status +
                ", accountSender=" + accountSender +
                ", accountReceiver=" + accountReceiver +
                ", balance=" + balance +
                ", userLogin=" + userLogin +
                '}';
    }
}
