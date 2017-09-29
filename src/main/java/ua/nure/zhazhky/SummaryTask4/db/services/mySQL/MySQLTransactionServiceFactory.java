package ua.nure.zhazhky.SummaryTask4.db.services.mySQL;

import ua.nure.zhazhky.SummaryTask4.db.services.DAOFactory;
import ua.nure.zhazhky.SummaryTask4.db.services.TransactionServiceFactory;
import ua.nure.zhazhky.SummaryTask4.db.services.mySQL.mySQLTransactions.MySQLAccountTransactionService;
import ua.nure.zhazhky.SummaryTask4.db.services.mySQL.mySQLTransactions.MySQLCreditCardTransactionService;
import ua.nure.zhazhky.SummaryTask4.db.services.mySQL.mySQLTransactions.MySQLPaymentTransactionService;
import ua.nure.zhazhky.SummaryTask4.db.services.mySQL.mySQLTransactions.MySQLUserTransactionService;
import ua.nure.zhazhky.SummaryTask4.db.transactions.AccountTransactionService;
import ua.nure.zhazhky.SummaryTask4.db.transactions.CreditCardTransactionService;
import ua.nure.zhazhky.SummaryTask4.db.transactions.PaymentTransactionService;
import ua.nure.zhazhky.SummaryTask4.db.transactions.UserTransactionService;

public class MySQLTransactionServiceFactory implements TransactionServiceFactory {

    private static MySQLTransactionServiceFactory mySQLTransactionServiceFactory;

    private DAOFactory daoFactory;

    private MySQLTransactionServiceFactory(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public static MySQLTransactionServiceFactory getInstance() {
        if (mySQLTransactionServiceFactory == null) {
            synchronized (MySQLTransactionServiceFactory.class) {
                if (mySQLTransactionServiceFactory == null) {
                    mySQLTransactionServiceFactory =
                            new MySQLTransactionServiceFactory(MySQLDAOFactory.getInstance());
                }
            }
        }
        return mySQLTransactionServiceFactory;
    }

    @Override
    public UserTransactionService getUserTransactionService() {
        return MySQLUserTransactionService.getInstance(daoFactory);
    }

    @Override
    public CreditCardTransactionService getCreditCardTransactionService() {
        return MySQLCreditCardTransactionService.getInstance(daoFactory);
    }

    @Override
    public AccountTransactionService getAccountTransactionService() {
        return MySQLAccountTransactionService.getInstance(daoFactory);
    }

    @Override
    public PaymentTransactionService getPaymentTransactionService() {
        return MySQLPaymentTransactionService.getInstance(daoFactory);
    }
}
