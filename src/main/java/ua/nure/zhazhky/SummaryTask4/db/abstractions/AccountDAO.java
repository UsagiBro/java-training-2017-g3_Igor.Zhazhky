package ua.nure.zhazhky.SummaryTask4.db.abstractions;

import ua.nure.zhazhky.SummaryTask4.model.Account;
import ua.nure.zhazhky.SummaryTask4.model.Role;
import ua.nure.zhazhky.SummaryTask4.model.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract class AccountDAO {

    public abstract boolean createAccount(Connection connection, Account account) throws SQLException;

    public abstract List<Account> getAccountsByUser(Connection connection, String login) throws SQLException;

    public abstract boolean updateAccountSetBalance(Connection connection, int balance, String accountId) throws SQLException;

    public abstract int getBalanceByAccountId(Connection connection, String accountId) throws SQLException;

    public abstract Account getAccountById(Connection connection, String accountId) throws SQLException;

    public abstract boolean blockAccount(Connection connection, String accountId) throws SQLException;

    public abstract boolean unBlockAccount(Connection connection, String accountId) throws SQLException;

    public abstract int getAllUserBalance(Connection connection, String login) throws SQLException;

    protected Account getAccount(ResultSet resultSet) throws SQLException {
        Account account = new Account();
        account.setAccountId(resultSet.getString("account_id"));
        account.setName(resultSet.getString("name"));
        account.setBalance(Integer.valueOf(resultSet.getString("balance")));
        account.setUser(getUser(resultSet));
        account.setBlocked(resultSet.getBoolean("blocked"));
        return account;
    }

    protected Account getAccountWithoutUser(ResultSet resultSet) throws SQLException {
        Account account = new Account();
        account.setAccountId(resultSet.getString("account_id"));
        account.setName(resultSet.getString("name"));
        account.setBalance(Integer.valueOf(resultSet.getString("balance")));
        account.setBlocked(resultSet.getBoolean("blocked"));
        return account;
    }

    private User getUser(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setLogin(resultSet.getString("login"));
        user.setPassword(resultSet.getString("password"));
        user.setFullName(resultSet.getString("fullname"));
        user.setBlocked(resultSet.getBoolean("blocked"));
        user.setRole(Role.getRoleById(resultSet.getInt("role_id")));
        return user;
    }
}
