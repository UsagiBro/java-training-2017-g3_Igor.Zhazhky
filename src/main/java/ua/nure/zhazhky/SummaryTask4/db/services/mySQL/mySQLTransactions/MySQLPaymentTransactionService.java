package ua.nure.zhazhky.SummaryTask4.db.services.mySQL.mySQLTransactions;

import org.apache.log4j.Logger;
import ua.nure.zhazhky.SummaryTask4.db.services.DAOFactory;
import ua.nure.zhazhky.SummaryTask4.db.transactions.PaymentTransactionService;
import ua.nure.zhazhky.SummaryTask4.exceptions.Messages;
import ua.nure.zhazhky.SummaryTask4.exceptions.database.DBException;
import ua.nure.zhazhky.SummaryTask4.model.Cheque;
import ua.nure.zhazhky.SummaryTask4.model.Payment;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class MySQLPaymentTransactionService implements PaymentTransactionService {

    private static MySQLPaymentTransactionService mySQLPaymentTransactionService;
    private static final Logger LOG = Logger.getLogger(MySQLPaymentTransactionService.class);

    private DAOFactory daoFactory;

    private MySQLPaymentTransactionService(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public static MySQLPaymentTransactionService getInstance(DAOFactory daoFactory) {
        if (mySQLPaymentTransactionService == null) {
            synchronized (MySQLUserTransactionService.class) {
                if (mySQLPaymentTransactionService == null) {
                    mySQLPaymentTransactionService = new MySQLPaymentTransactionService(daoFactory);
                }
            }
        }
        return mySQLPaymentTransactionService;
    }

    @Override
    public boolean createPayment(Payment payment, String userLogin) throws DBException {
        Connection connection = daoFactory.getConnection();
        boolean result;
        try {
            result = daoFactory.getPaymentDAO().createPayment(connection, payment, userLogin);
        } catch (SQLException e) {
            LOG.error(Messages.ERR_CANNOT_CREATE_PAYMENT, e);
            daoFactory.rollback(connection);
            throw new DBException(Messages.ERR_CANNOT_CREATE_PAYMENT, e);
        }
        daoFactory.commit(connection);
        daoFactory.close(connection);
        return result;
    }

    @Override
    public boolean updatePaymentSetStatusAndAccountsSetBalance
            (Cheque cheque)
            throws DBException {
        Connection connection = daoFactory.getConnection();
        boolean result = false;
        try {
            result = daoFactory.getAccountDAO().updateAccountSetBalance(
                    connection, cheque.getSenderSum(), cheque.getSenderId());
            result &= daoFactory.getAccountDAO().updateAccountSetBalance(
                    connection, cheque.getReceiverSum(), cheque.getReceiverId());
            result &= daoFactory.getPaymentDAO().updateStatus(connection, cheque);
        } catch (SQLException e) {
            LOG.error(Messages.ERR_CANNOT_UPDATE_PAYMENT_AND_ACCOUNTS, e);
            daoFactory.rollback(connection);
            throw new DBException(Messages.ERR_CANNOT_UPDATE_PAYMENT_AND_ACCOUNTS, e);
        }
        daoFactory.commit(connection);
        daoFactory.close(connection);
        return result;
    }

    @Override
    public List<Payment> getPaymentsByUserLogin(String userLogin) throws DBException {
        List<Payment> list;
        Connection connection = daoFactory.getConnection();
        try {
            list = daoFactory.getPaymentDAO().getPaymentsByUserLogin(connection, userLogin);
        } catch (SQLException e) {
            LOG.error(Messages.ERR_CANNOT_GET_PAYMENTS_BY_USER, e);
            daoFactory.rollback(connection);
            throw new DBException(Messages.ERR_CANNOT_GET_PAYMENTS_BY_USER, e);
        }
        daoFactory.commit(connection);
        daoFactory.close(connection);
        return list;
    }

    @Override
    public boolean deletePreparedPayments(String login) throws DBException {
        Connection connection = daoFactory.getConnection();
        boolean result;
        try {
            result = daoFactory.getPaymentDAO().deletePreparedPayments(connection, login);
        } catch (SQLException e) {
            LOG.error(Messages.ERR_CANNOT_DELETE_PAYMENTS, e);
            daoFactory.rollback(connection);
            throw new DBException(Messages.ERR_CANNOT_DELETE_PAYMENTS, e);
        }
        daoFactory.commit(connection);
        daoFactory.close(connection);
        return result;
    }
}
