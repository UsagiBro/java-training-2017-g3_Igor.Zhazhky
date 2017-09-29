package ua.nure.zhazhky.SummaryTask4.db.services.mySQL.mySQLTransactions;

import org.apache.log4j.Logger;
import ua.nure.zhazhky.SummaryTask4.db.services.DAOFactory;
import ua.nure.zhazhky.SummaryTask4.db.transactions.UserTransactionService;
import ua.nure.zhazhky.SummaryTask4.exceptions.Messages;
import ua.nure.zhazhky.SummaryTask4.exceptions.database.DBException;
import ua.nure.zhazhky.SummaryTask4.model.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class MySQLUserTransactionService implements UserTransactionService {

    private static final Logger LOG = Logger.getLogger(MySQLUserTransactionService.class);

    private static MySQLUserTransactionService mySQLUserTransactionService;

    private DAOFactory daoFactory;

    private MySQLUserTransactionService(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public static MySQLUserTransactionService getInstance(DAOFactory daoFactory) {
        if (mySQLUserTransactionService == null) {
            synchronized (MySQLUserTransactionService.class) {
                if (mySQLUserTransactionService == null) {
                    mySQLUserTransactionService = new MySQLUserTransactionService(daoFactory);
                }
            }
        }
        return mySQLUserTransactionService;
    }

    @Override
    public boolean createUser(User user) throws DBException {
        Connection connection = daoFactory.getConnection();
        boolean result;
        try {
            result = daoFactory.getUserDAO().createUser(connection, user);
        } catch (SQLException e) {
            LOG.error(Messages.ERR_CANNOT_CREATE_USER, e);
            daoFactory.rollback(connection);
            throw new DBException(Messages.ERR_CANNOT_CREATE_USER, e);
        }
        daoFactory.commit(connection);
        daoFactory.close(connection);
        return result;
    }

    @Override
    public List<User> getAllUsers() throws DBException {
        Connection connection = daoFactory.getConnection();
        List<User> users;
        try {
            users = daoFactory.getUserDAO().getAllUsers(connection);
        } catch (SQLException e) {
            LOG.error(Messages.ERR_CANNOT_GET_USERS, e);
            daoFactory.rollback(connection);
            throw new DBException(Messages.ERR_CANNOT_GET_USERS, e);
        }
        return users;
    }

    @Override
    public User getUserByLoginAndPassword(String login, String password) throws DBException {
        Connection connection = daoFactory.getConnection();
        User user;
        try {
            user = daoFactory.getUserDAO().getUserByLoginAndPassword(connection, login, password);
        } catch (SQLException ex) {
            LOG.error(Messages.ERR_CANNOT_GET_USER_BY_LOGIN_AND_PASSWORD, ex);
            throw new DBException(Messages.ERR_CANNOT_GET_USER_BY_LOGIN_AND_PASSWORD, ex);
        }
        daoFactory.close(connection);
        return user;
    }

    @Override
    public boolean updateUserByLogin(User user, String oldLogin) throws DBException {
        Connection connection = daoFactory.getConnection();
        boolean result;
        try {
            result = daoFactory.getUserDAO().updateUser(connection, user, oldLogin);
        } catch (SQLException e) {
            LOG.error(Messages.ERR_CANNOT_UPDATE_USER, e);
            daoFactory.rollback(connection);
            throw new DBException(Messages.ERR_CANNOT_UPDATE_USER, e);
        }
        daoFactory.commit(connection);
        daoFactory.close(connection);
        return result;
    }

    @Override
    public boolean setUserFieldBlockedByUserLogin(boolean blocked, String userLogin) throws DBException {
        Connection connection = daoFactory.getConnection();
        boolean result = false;
        try {
            result = daoFactory.getUserDAO().setUserFieldBlockedByUserLogin(connection, blocked, userLogin);
        } catch (SQLException e) {
            LOG.error(Messages.ERR_CANNOT_BLOCK_OR_UNBLOCK_USER, e);
            daoFactory.rollback(connection);
            throw new DBException(Messages.ERR_CANNOT_BLOCK_OR_UNBLOCK_USER, e);
        }
        daoFactory.commit(connection);
        daoFactory.close(connection);
        return result;
    }
}
