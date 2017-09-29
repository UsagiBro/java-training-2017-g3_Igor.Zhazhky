package ua.nure.zhazhky.SummaryTask4.db.services.mySQL.mySQLTransactions;

import org.apache.log4j.Logger;
import ua.nure.zhazhky.SummaryTask4.db.services.DAOFactory;
import ua.nure.zhazhky.SummaryTask4.db.transactions.CreditCardTransactionService;
import ua.nure.zhazhky.SummaryTask4.exceptions.Messages;
import ua.nure.zhazhky.SummaryTask4.exceptions.database.DBException;
import ua.nure.zhazhky.SummaryTask4.model.CreditCard;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class MySQLCreditCardTransactionService implements CreditCardTransactionService {

    private static MySQLCreditCardTransactionService mySQLCreditCardTransactionService;
    private static final Logger LOG = Logger.getLogger(MySQLCreditCardTransactionService.class);

    private DAOFactory daoFactory;

    private MySQLCreditCardTransactionService(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public static MySQLCreditCardTransactionService getInstance(DAOFactory daoFactory) {
        if (mySQLCreditCardTransactionService == null) {
            synchronized (MySQLUserTransactionService.class) {
                if (mySQLCreditCardTransactionService == null) {
                    mySQLCreditCardTransactionService = new MySQLCreditCardTransactionService(daoFactory);
                }
            }
        }
        return mySQLCreditCardTransactionService;
    }

    @Override
    public boolean createCreditCard(CreditCard creditCard) throws DBException {
        Connection connection = daoFactory.getConnection();
        boolean result;
        try {
            result = daoFactory.getCreditCardDAO().createCard(connection, creditCard);
        } catch (SQLException e) {
            LOG.error(Messages.ERR_CANNOT_CREATE_CARD, e);
            daoFactory.rollback(connection);
            throw new DBException(Messages.ERR_CANNOT_CREATE_CARD, e);
        }
        daoFactory.commit(connection);
        daoFactory.close(connection);
        return result;
    }

    @Override
    public List<CreditCard> getCardsByAccount(String accountId) throws DBException {
        List<CreditCard> list;
        Connection connection = daoFactory.getConnection();
        try {
            list = daoFactory.getCreditCardDAO().getCardsByAccount(connection, accountId);
        } catch (SQLException e) {
            LOG.error(Messages.ERR_CANNOT_GET_CARDS_BY_ACCOUNT, e);
            daoFactory.rollback(connection);
            throw new DBException(Messages.ERR_CANNOT_GET_CARDS_BY_ACCOUNT, e);
        }
        daoFactory.commit(connection);
        daoFactory.close(connection);
        return list;
    }

//    @Override
//    public List<CreditCard> getCardsByUserLogin(String login) throws DBException {
//        List<CreditCard> cards;
//        Connection connection = daoFactory.getConnection();
//        try {
//            cards = daoFactory.getCreditCardDAO().getCardsByAccount(connection, login);
//        } catch (SQLException e) {
//            LOG.error(Messages.ERR_CANNOT_GET_CARDS_BY_USER_LOGIN, e);
//            daoFactory.rollback(connection);
//            throw new DBException(Messages.ERR_CANNOT_GET_CARDS_BY_USER_LOGIN, e);
//        }
//        daoFactory.commit(connection);
//        daoFactory.close(connection);
//        return cards;
//    }
}
