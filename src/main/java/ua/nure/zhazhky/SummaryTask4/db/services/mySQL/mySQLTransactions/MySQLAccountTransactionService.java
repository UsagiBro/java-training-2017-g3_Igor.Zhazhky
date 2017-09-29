package ua.nure.zhazhky.SummaryTask4.db.services.mySQL.mySQLTransactions;

import org.apache.log4j.Logger;
import ua.nure.zhazhky.SummaryTask4.db.services.DAOFactory;
import ua.nure.zhazhky.SummaryTask4.db.transactions.AccountTransactionService;
import ua.nure.zhazhky.SummaryTask4.exceptions.Messages;
import ua.nure.zhazhky.SummaryTask4.exceptions.database.DBException;
import ua.nure.zhazhky.SummaryTask4.model.Account;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class MySQLAccountTransactionService implements AccountTransactionService {

    private static final Logger LOG = Logger.getLogger(MySQLAccountTransactionService.class);
    private static MySQLAccountTransactionService mySQLAccountTransactionService;

    private DAOFactory daoFactory;

    private MySQLAccountTransactionService(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public static MySQLAccountTransactionService getInstance(DAOFactory daoFactory) {
        if (mySQLAccountTransactionService == null) {
            synchronized (MySQLUserTransactionService.class) {
                if (mySQLAccountTransactionService == null) {
                    mySQLAccountTransactionService = new MySQLAccountTransactionService(daoFactory);
                }
            }
        }
        return mySQLAccountTransactionService;
    }

    @Override
    public boolean createAccount(Account account) throws DBException {
        Connection connection = daoFactory.getConnection();
        boolean result;
        try {
            result = daoFactory.getAccountDAO().createAccount(connection, account);
        } catch (SQLException e) {
            LOG.error(Messages.ERR_CANNOT_CREATE_ACCOUNT, e);
            daoFactory.rollback(connection);
            throw new DBException(Messages.ERR_CANNOT_CREATE_ACCOUNT, e);
        }
        daoFactory.commit(connection);
        daoFactory.close(connection);
        return result;
    }

    @Override
    public List<Account> getAccountsByUserLogin(String login) throws DBException {
        List<Account> list;
        Connection connection = daoFactory.getConnection();
        try {
            list = daoFactory.getAccountDAO().getAccountsByUser(connection, login);
        } catch (SQLException e) {
            LOG.error(Messages.ERR_CANNOT_GET_ACCOUNTS_BY_USER, e);
            daoFactory.rollback(connection);
            throw new DBException(Messages.ERR_CANNOT_GET_ACCOUNTS_BY_USER, e);
        }
        daoFactory.commit(connection);
        daoFactory.close(connection);
        return list;
    }

    @Override
    public boolean updateAccountSetBalance(int balance, String accountId) throws DBException {
        Connection connection = daoFactory.getConnection();
        boolean result = false;
        try {
            result = daoFactory.getAccountDAO().updateAccountSetBalance(connection, balance, accountId);
        } catch (SQLException e) {
            LOG.error(Messages.ERR_CANNOT_GET_ACCOUNTS_BY_USER, e);
            daoFactory.rollback(connection);
            throw new DBException(Messages.ERR_CANNOT_GET_ACCOUNTS_BY_USER, e);
        }
        daoFactory.commit(connection);
        daoFactory.close(connection);
        return result;
    }

    @Override
    public int getBalanceByAccountId(String accountId) throws DBException {
        Connection connection = daoFactory.getConnection();
        int result = 0;
        try {
            result = daoFactory.getAccountDAO().getBalanceByAccountId(connection, accountId);
        } catch (SQLException e) {
            LOG.error(Messages.ERR_CANNOT_GET_BALANCE_BY_ACCOUNT_ID, e);
            daoFactory.rollback(connection);
            throw new DBException(Messages.ERR_CANNOT_GET_BALANCE_BY_ACCOUNT_ID, e);
        }
        daoFactory.commit(connection);
        daoFactory.close(connection);
        return result;
    }

    @Override
    public Account getAccountById(String accountId) throws DBException {
        Connection connection = daoFactory.getConnection();
        Account account;
        try {
            account = daoFactory.getAccountDAO().getAccountById(connection, accountId);
        } catch (SQLException e) {
            LOG.error(Messages.ERR_CANNOT_GET_ACCOUNT_BY_ID, e);
            throw new DBException(Messages.ERR_CANNOT_GET_ACCOUNT_BY_ID, e);
        }
        daoFactory.close(connection);
        return account;
    }

    @Override
    public boolean blockAccount(String accountId) throws DBException {
        Connection connection = daoFactory.getConnection();
        boolean result = false;
        try {
            result = daoFactory.getAccountDAO().blockAccount(connection, accountId);
        } catch (SQLException e) {
            LOG.error(Messages.ERR_CANNOT_BLOCK_ACCOUNT, e);
            daoFactory.rollback(connection);
            throw new DBException(Messages.ERR_CANNOT_BLOCK_ACCOUNT, e);
        }
        daoFactory.commit(connection);
        daoFactory.close(connection);
        return result;
    }

    @Override
    public boolean unblockAccount(String accountId) throws DBException {
        Connection connection = daoFactory.getConnection();
        boolean result = false;
        try {
            result = daoFactory.getAccountDAO().unBlockAccount(connection, accountId);
        } catch (SQLException e) {
            LOG.error(Messages.ERR_CANNOT_UNBLOCK_ACCOUNT, e);
            daoFactory.rollback(connection);
            throw new DBException(Messages.ERR_CANNOT_UNBLOCK_ACCOUNT, e);
        }
        daoFactory.commit(connection);
        daoFactory.close(connection);
        return result;
    }

    @Override
    public int getAllUserBalance(String login) throws DBException {
        Connection connection = daoFactory.getConnection();
        int result = 0;
        try {
            result = daoFactory.getAccountDAO().getAllUserBalance(connection, login);
        } catch (SQLException e) {
            LOG.error(Messages.ERR_CANNOT_GET_BALANCE_BY_ACCOUNT_ID, e);
            daoFactory.rollback(connection);
            throw new DBException(Messages.ERR_CANNOT_GET_BALANCE_BY_ACCOUNT_ID, e);
        }
        daoFactory.commit(connection);
        daoFactory.close(connection);
        return result;
    }
}
