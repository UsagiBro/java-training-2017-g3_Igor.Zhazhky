package ua.nure.zhazhky.SummaryTask4.db.transactions;

import ua.nure.zhazhky.SummaryTask4.exceptions.database.DBException;
import ua.nure.zhazhky.SummaryTask4.model.Account;

import java.util.List;

public interface AccountTransactionService {

    boolean createAccount(Account account) throws DBException;

    List<Account> getAccountsByUserLogin(String login) throws  DBException;

    boolean updateAccountSetBalance(int balance, String accountId) throws DBException;

    int getBalanceByAccountId(String accountId) throws DBException;

    Account getAccountById(String accountId) throws DBException;

    boolean blockAccount(String accountId) throws DBException;

    boolean unblockAccount(String accountId) throws DBException;

    int getAllUserBalance(String login) throws DBException;
}
