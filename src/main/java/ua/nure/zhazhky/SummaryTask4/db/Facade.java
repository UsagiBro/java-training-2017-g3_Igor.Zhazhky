package ua.nure.zhazhky.SummaryTask4.db;

import ua.nure.zhazhky.SummaryTask4.exceptions.database.DBException;
import ua.nure.zhazhky.SummaryTask4.model.*;

import java.util.List;

public interface Facade {

    // User
    boolean createUser(User user) throws DBException;

    User getUserByLoginAndPassword(String login, String password) throws DBException;

    List<User> getAllUsers() throws DBException;

    boolean updateUserByLogin(User user, String oldLogin) throws DBException;

    boolean setUserFieldFullnameByUserLogin(String fullname, String userLogin) throws DBException;

    boolean setUserFieldBlockedByUserLogin(boolean blocked, String userLogin) throws DBException;

    //Account
    boolean createAccount(Account account) throws DBException;

    Account getAccountById(String accountId) throws DBException;

    boolean updateAccountSetBalance(int balance, String accountId) throws DBException;

    boolean blockAccount(String accountId) throws DBException;

    boolean unblockAccount(String accountId) throws DBException;

    List<Account> getAccountsByUserLogin(String login) throws DBException;

    int getBalanceByAccountId(String accountId) throws DBException;

    int getAllUserBalance(String login) throws DBException;

    //Credit card
    boolean createCreditCard(CreditCard creditCard) throws DBException;

    List<CreditCard> getCardsByAccount(String accountId) throws DBException;

    //Payment
    boolean createPayment(Payment payment, String userLogin) throws DBException;

    boolean updatePaymentSetStatusAndAccountsSetBalance(Cheque cheque) throws DBException;

    List<Payment> getPaymentsByUserLogin(String userLogin) throws DBException;

    boolean deletePreparedPayments(String login) throws DBException;
}
