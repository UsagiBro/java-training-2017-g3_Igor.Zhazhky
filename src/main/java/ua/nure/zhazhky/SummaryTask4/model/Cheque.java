package ua.nure.zhazhky.SummaryTask4.model;

public class Cheque {
    private int senderSum;
    private int receiverSum;
    private String senderId;
    private String receiverId;
    private int balance;
    private String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getSenderSum() {
        return senderSum;
    }

    public void setSenderSum(int senderSum) {
        this.senderSum = senderSum;
    }

    public int getReceiverSum() {
        return receiverSum;
    }

    public void setReceiverSum(int receiverSum) {
        this.receiverSum = receiverSum;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Cheque{" +
                "date=" + date +
                "senderSum=" + senderSum +
                ", receiverSum=" + receiverSum +
                ", senderId='" + senderId + '\'' +
                ", receiverId='" + receiverId + '\'' +
                ", balance='" + balance + '\'' +
                '}';
    }
}
