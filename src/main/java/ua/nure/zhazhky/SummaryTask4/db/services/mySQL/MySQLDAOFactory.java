package ua.nure.zhazhky.SummaryTask4.db.services.mySQL;

import org.apache.log4j.Logger;
import ua.nure.zhazhky.SummaryTask4.db.abstractions.AccountDAO;
import ua.nure.zhazhky.SummaryTask4.db.abstractions.CreditCardDAO;
import ua.nure.zhazhky.SummaryTask4.db.abstractions.PaymentDAO;
import ua.nure.zhazhky.SummaryTask4.db.abstractions.UserDAO;
import ua.nure.zhazhky.SummaryTask4.db.services.DAOFactory;
import ua.nure.zhazhky.SummaryTask4.db.services.mySQL.mysqlDAO.MySQLAccountDAO;
import ua.nure.zhazhky.SummaryTask4.db.services.mySQL.mysqlDAO.MySQLCreditCardDAO;
import ua.nure.zhazhky.SummaryTask4.db.services.mySQL.mysqlDAO.MySQLPaymentDAO;
import ua.nure.zhazhky.SummaryTask4.db.services.mySQL.mysqlDAO.MySQLUserDAO;
import ua.nure.zhazhky.SummaryTask4.exceptions.Messages;
import ua.nure.zhazhky.SummaryTask4.exceptions.database.DBException;
import ua.nure.zhazhky.SummaryTask4.exceptions.database.DBStartupException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class MySQLDAOFactory implements DAOFactory {

    private static MySQLDAOFactory mySQLDAOFactory;
    private DataSource dataSource;

    private static final Logger LOG = Logger.getLogger(MySQLDAOFactory.class);

    private MySQLDAOFactory(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static MySQLDAOFactory getInstance() {
        if (mySQLDAOFactory == null) {
            synchronized (MySQLDAOFactory.class) {
                if (mySQLDAOFactory == null) {
                    try {
                        Context initContext = new InitialContext();
                        Context envContext = (Context) initContext.lookup("java:/comp/env");
                        DataSource dataSource = (DataSource) envContext.lookup("jdbc/KKK");
                        mySQLDAOFactory = new MySQLDAOFactory(dataSource);
                        LOG.trace("Data source --> " + dataSource);
                    } catch (NamingException e) {
                        LOG.error(Messages.ERR_CANNOT_OBTAIN_DATA_SOURCE, e);
                        throw new DBStartupException(Messages.ERR_CANNOT_OBTAIN_DATA_SOURCE, e);
                    }
                }
            }
        }
        return mySQLDAOFactory;
    }

    // //////////////////////////////////////////////////////////
    // GetDAO methods
    // //////////////////////////////////////////////////////////
    @Override
    public UserDAO getUserDAO() {

        return MySQLUserDAO.getInstance();
    }

    @Override
    public CreditCardDAO getCreditCardDAO() {

        return MySQLCreditCardDAO.getInstance();
    }

    @Override
    public AccountDAO getAccountDAO() {

        return MySQLAccountDAO.getInstance();
    }

    @Override
    public PaymentDAO getPaymentDAO() {

        return MySQLPaymentDAO.getInstance();
    }

    // //////////////////////////////////////////////////////////
    // Connection methods
    // //////////////////////////////////////////////////////////
    public Connection getConnection() throws DBException {
        try {
            return dataSource.getConnection();
        } catch (SQLException ex) {
            LOG.error(Messages.ERR_CANNOT_GET_CONNECTION_MESSAGE, ex);
            throw new DBException(Messages.ERR_CANNOT_GET_CONNECTION_MESSAGE, ex);
        }
    }

    public void close(Connection con) throws DBException {
        try {
            con.close();
        } catch (SQLException ex) {
            LOG.error(Messages.ERR_CANNOT_CLOSE_CONNECTION, ex);
            throw new DBException(Messages.ERR_CANNOT_CLOSE_CONNECTION, ex);
        }
    }

    // //////////////////////////////////////////////////////////
    // Transaction methods
    // //////////////////////////////////////////////////////////
    public void commit(Connection con) throws DBException {
        try {
            con.commit();
        } catch (SQLException ex) {
            LOG.error(Messages.ERR_FAILED_TO_MAKE_CHANGES, ex);
            throw new DBException(Messages.ERR_FAILED_TO_MAKE_CHANGES, ex);
        }
    }

    public void rollback(Connection con) throws DBException {
        if (con != null) {
            try {
                con.rollback();
            } catch (SQLException ex) {
                LOG.error(Messages.ERR_CANNOT_ROLLBACK_TRANSACTION, ex);
                throw new DBException(Messages.ERR_CANNOT_ROLLBACK_TRANSACTION, ex);
            }
        }
    }
}
