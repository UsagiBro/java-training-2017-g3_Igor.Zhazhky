package ua.nure.zhazhky.SummaryTask4.db.abstractions;

import ua.nure.zhazhky.SummaryTask4.model.Cheque;
import ua.nure.zhazhky.SummaryTask4.model.Payment;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract class PaymentDAO {

    public abstract boolean createPayment(Connection connection, Payment payment, String userLogin)
            throws SQLException;

    public abstract boolean updateStatus(
            Connection connection, Cheque cheque) throws SQLException;

    public abstract List<Payment> getPaymentsByUserLogin(Connection connection, String userLogin) throws SQLException;

    public abstract boolean deletePreparedPayments(Connection connection, String login) throws SQLException;

    public Payment getPayment(ResultSet resultSet) throws SQLException {
        Payment payment = new Payment();
        payment.setDate(resultSet.getString("date"));
        payment.setStatus(resultSet.getString("status"));
        payment.setAccountSender(resultSet.getString("senderId"));
        payment.setAccountReceiver(resultSet.getString("receiverId"));
        payment.setBalance(resultSet.getInt("balance"));
        payment.setUserLogin(resultSet.getString("userLogin"));
        return payment;
    }
}
