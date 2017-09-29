package ua.nure.zhazhky.SummaryTask4.db;

import ua.nure.zhazhky.SummaryTask4.db.services.TransactionServiceFactory;
import ua.nure.zhazhky.SummaryTask4.exceptions.database.DBException;
import ua.nure.zhazhky.SummaryTask4.model.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TransactionServiceFacade implements Facade {

    private static Map<TransactionServiceFactory, TransactionServiceFacade> abstractFactoryMap = new HashMap<>();
    private static TransactionServiceFactory transactionServiceFactory;

    private TransactionServiceFacade(TransactionServiceFactory abstractFactory) {
        transactionServiceFactory = abstractFactory;
    }

    public static TransactionServiceFacade getInstance(TransactionServiceFactory transactionServiceFactory) {
        if (!abstractFactoryMap.containsKey(transactionServiceFactory)) {
            synchronized (TransactionServiceFacade.class) {
                if (!abstractFactoryMap.containsKey(transactionServiceFactory)) {
                    TransactionServiceFacade facade = new TransactionServiceFacade(transactionServiceFactory);
                    abstractFactoryMap.put(transactionServiceFactory, facade);
                    return facade;
                }
            }
        }
        return abstractFactoryMap.get(transactionServiceFactory);
    }

    ////////////////////
    ///////////User
    ////////////////////
    @Override
    public boolean createUser(User user) throws DBException {
        return transactionServiceFactory.getUserTransactionService().createUser(user);
    }

    @Override
    public User getUserByLoginAndPassword(String login, String password) throws DBException {
        return transactionServiceFactory.getUserTransactionService().getUserByLoginAndPassword(login, password);
    }

    @Override
    public List<User> getAllUsers() throws DBException {
        return transactionServiceFactory.getUserTransactionService().getAllUsers();
    }

    @Override
    public boolean updateUserByLogin(User user, String oldLogin) throws DBException {
        return transactionServiceFactory.getUserTransactionService().updateUserByLogin(user, oldLogin);
    }

    @Override
    public boolean setUserFieldFullnameByUserLogin(String fullname, String userLogin) throws DBException {
        return false;
    }

    @Override
    public boolean setUserFieldBlockedByUserLogin(boolean blocked, String userLogin) throws DBException {
        return transactionServiceFactory.getUserTransactionService().setUserFieldBlockedByUserLogin(blocked, userLogin);
    }


    ////////////////////
    ///////////Account
    ////////////////////
    @Override
    public boolean createAccount(Account account) throws DBException {
        return transactionServiceFactory.getAccountTransactionService().createAccount(account);
    }

    @Override
    public Account getAccountById(String accountId) throws DBException {
        return transactionServiceFactory.getAccountTransactionService().getAccountById(accountId);
    }

    @Override
    public boolean updateAccountSetBalance(int balance, String accountId) throws DBException {
        return transactionServiceFactory.getAccountTransactionService().updateAccountSetBalance(balance, accountId);
    }

    @Override
    public boolean blockAccount(String accountId) throws DBException {
        return transactionServiceFactory.getAccountTransactionService().blockAccount(accountId);
    }

    @Override
    public boolean unblockAccount(String accountId) throws DBException {
        return transactionServiceFactory.getAccountTransactionService().unblockAccount(accountId);
    }

    public List<Account> getAccountsByUserLogin(String login) throws DBException {
        return transactionServiceFactory.getAccountTransactionService().getAccountsByUserLogin(login);
    }

    @Override
    public int getBalanceByAccountId(String accountId) throws DBException {
        return transactionServiceFactory.getAccountTransactionService().getBalanceByAccountId(accountId);
    }

    @Override
    public int getAllUserBalance(String login) throws DBException {
        return transactionServiceFactory.getAccountTransactionService().getAllUserBalance(login);
    }

    ////////////////////
    ///////////CreditCard
    ////////////////////
    @Override
    public boolean createCreditCard(CreditCard creditCard) throws DBException {
        return transactionServiceFactory.getCreditCardTransactionService().createCreditCard(creditCard);
    }

    @Override
    public List<CreditCard> getCardsByAccount(String accountId) throws DBException {
        return transactionServiceFactory.getCreditCardTransactionService().getCardsByAccount(accountId);
    }

    ///////////////////
    /////////Payment
    ///////////////////
    @Override
    public boolean createPayment(Payment payment, String userLogin) throws DBException {
        return transactionServiceFactory.getPaymentTransactionService().createPayment(payment, userLogin);
    }

    @Override
    public boolean updatePaymentSetStatusAndAccountsSetBalance(Cheque cheque) throws DBException {
        return transactionServiceFactory.
                getPaymentTransactionService().updatePaymentSetStatusAndAccountsSetBalance(cheque);
    }

    @Override
    public List<Payment> getPaymentsByUserLogin(String userLogin) throws DBException {
        return transactionServiceFactory.getPaymentTransactionService().getPaymentsByUserLogin(userLogin);
    }

    @Override
    public boolean deletePreparedPayments(String login) throws DBException {
        return transactionServiceFactory.getPaymentTransactionService().deletePreparedPayments(login);
    }
}
