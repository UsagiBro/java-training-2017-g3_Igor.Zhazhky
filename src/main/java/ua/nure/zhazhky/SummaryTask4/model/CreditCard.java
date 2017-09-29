package ua.nure.zhazhky.SummaryTask4.model;

import java.io.Serializable;

public class CreditCard implements Serializable{

    private String cardId;
    private String accountId;
    private String password;

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "CreditCard{" +
                "cardId=" + cardId +
                "accountId=" + accountId +
                "password=" + password +
                '}';
    }
}
