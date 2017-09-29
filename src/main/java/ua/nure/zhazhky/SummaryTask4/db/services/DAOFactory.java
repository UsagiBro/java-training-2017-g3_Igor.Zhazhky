package ua.nure.zhazhky.SummaryTask4.db.services;

import ua.nure.zhazhky.SummaryTask4.db.abstractions.AccountDAO;
import ua.nure.zhazhky.SummaryTask4.db.abstractions.CreditCardDAO;
import ua.nure.zhazhky.SummaryTask4.db.abstractions.PaymentDAO;
import ua.nure.zhazhky.SummaryTask4.db.abstractions.UserDAO;
import ua.nure.zhazhky.SummaryTask4.exceptions.database.DBException;

import java.sql.Connection;

public interface DAOFactory {

    Connection getConnection() throws DBException;

    UserDAO getUserDAO();

    CreditCardDAO getCreditCardDAO();

    AccountDAO getAccountDAO();

    PaymentDAO getPaymentDAO();

    void close(Connection connection) throws DBException;

    void commit(Connection connection) throws DBException;

    void rollback(Connection connection) throws DBException;
}
