package ua.nure.zhazhky.SummaryTask4.db.services.mySQL.mysqlDAO;

import ua.nure.zhazhky.SummaryTask4.db.abstractions.PaymentDAO;
import ua.nure.zhazhky.SummaryTask4.db.dbUtils.DBUtil;
import ua.nure.zhazhky.SummaryTask4.model.Cheque;
import ua.nure.zhazhky.SummaryTask4.model.Payment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySQLPaymentDAO extends PaymentDAO{

    private static final String SQL_INSERT_INTO_PAYMENTS =
            "INSERT INTO payments VALUES (DEFAULT, ?, DEFAULT, ?, ?, ?, ?)";
    private static final String SQL_UPDATE_STATUS_BY_BALANCE_AND_ACCOUNTS =
            "UPDATE payments SET status = 'sent' WHERE date = ? AND balance = ?";
    private static final String SQL_SELECT_FROM_PAYMENTS_BY_USER_LOGIN =
            "SELECT * FROM PAYMENTS WHERE userLogin = ?";
    private static final String SQL_DELETE_PREPARED_PAYMENTS =
            "DELETE FROM payments WHERE status = 'prepared' AND userLogin = ?";
    private static MySQLPaymentDAO mySQLPaymentDAO;

    private MySQLPaymentDAO() {}

    public static MySQLPaymentDAO getInstance() {
        if (mySQLPaymentDAO == null) {
            synchronized (MySQLAccountDAO.class) {
                if (mySQLPaymentDAO == null) {
                    mySQLPaymentDAO = new MySQLPaymentDAO();
                }
            }
        }
        return mySQLPaymentDAO;
    }

    @Override
    public boolean createPayment(Connection connection, Payment payment, String userLogin) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_INTO_PAYMENTS)) {
            DBUtil.fillPreparedStatement(
                    preparedStatement,
                    payment.getDate(),
                    payment.getAccountSender(),
                    payment.getAccountReceiver(),
                    payment.getBalance(),
                    userLogin);
            return preparedStatement.executeUpdate() > 0;
        }
    }

    @Override
    public boolean updateStatus(
            Connection connection, Cheque cheque)
            throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_STATUS_BY_BALANCE_AND_ACCOUNTS)) {
            DBUtil.fillPreparedStatement(
                    preparedStatement,
                    cheque.getDate(),
                    cheque.getBalance());
            return preparedStatement.executeUpdate() > 0;
        }
    }

    @Override
    public List<Payment> getPaymentsByUserLogin(Connection connection, String userLogin) throws SQLException {
        List<Payment> list = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_FROM_PAYMENTS_BY_USER_LOGIN)) {
            preparedStatement.setString(1, userLogin);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    list.add(getPayment(resultSet));
                }
            }
        }
        return list;
    }

    @Override
    public boolean deletePreparedPayments(Connection connection, String login) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_PREPARED_PAYMENTS)) {
            DBUtil.fillPreparedStatement(
                    preparedStatement,
                    login);
            return preparedStatement.executeUpdate() > 0;
        }
    }
}
