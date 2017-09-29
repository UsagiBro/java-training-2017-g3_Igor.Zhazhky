package ua.nure.zhazhky.SummaryTask4.db.services.mySQL.mysqlDAO;

import ua.nure.zhazhky.SummaryTask4.db.abstractions.AccountDAO;
import ua.nure.zhazhky.SummaryTask4.db.dbUtils.DBUtil;
import ua.nure.zhazhky.SummaryTask4.model.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySQLAccountDAO extends AccountDAO {

    private static final String SQL_INSERT_INTO_ACCOUNTS =
            "INSERT INTO accounts VALUES (?, DEFAULT, ?, ?, DEFAULT)";

    private static final String SQL_SELECT_SUM_BALANCE =
            "SELECT SUM(balance) FROM accounts WHERE userLogin=? AND blocked=FALSE";
    private static final String SQL_SELECT_FROM_ACCOUNTS_BY_USER_LOGIN =
            "SELECT * FROM accounts a INNER JOIN users u ON ? = u.login WHERE userLogin = ?";
    private static final String SQL_UPDATE_ACCOUNT_BALANCE =
            "UPDATE accounts SET balance = ? WHERE account_id = ?";
    private static final String SQL_SELECT_BALANCE_FROM_ACCOUNT =
            "SELECT balance FROM accounts WHERE account_id = ?";
    private static final String SQL_GET_ACCOUNT_BY_ID =
            "SELECT * FROM accounts  WHERE account_id = ?";
    private static final String SQL_BLOCK_ACCOUNT = "UPDATE accounts SET blocked = TRUE WHERE account_id = ?";
    private static final String SQL_UNBLOCK_ACCOUNT = "UPDATE accounts SET blocked = FALSE WHERE account_id = ?";
    private static MySQLAccountDAO mySQLAccountDAO;

    private MySQLAccountDAO() {}

    public static MySQLAccountDAO getInstance() {
        if (mySQLAccountDAO == null) {
            synchronized (MySQLAccountDAO.class) {
                if (mySQLAccountDAO == null) {
                    mySQLAccountDAO = new MySQLAccountDAO();
                }
            }
        }
        return mySQLAccountDAO;
    }

    @Override
    public boolean createAccount(Connection connection, Account account) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_INTO_ACCOUNTS)) {
            DBUtil.fillPreparedStatement(
                    preparedStatement,
                    account.getAccountId(),
                    account.getName(),
                    account.getUser().getLogin());
            return preparedStatement.executeUpdate() > 0;
        }
    }

    @Override
    public List<Account> getAccountsByUser(Connection connection, String login) throws SQLException {
        List<Account> list = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_FROM_ACCOUNTS_BY_USER_LOGIN)) {
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, login);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    list.add(getAccount(resultSet));
                }
            }
        }
        return list;
    }

    @Override
    public boolean updateAccountSetBalance(Connection connection, int balance, String accountId) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_ACCOUNT_BALANCE)) {
            DBUtil.fillPreparedStatement(
                    preparedStatement,
                    balance,
                    accountId);

            return preparedStatement.executeUpdate() > 0;
        }
    }

    @Override
    public int getBalanceByAccountId(Connection connection, String accountId) throws SQLException {
        int result = 0;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_BALANCE_FROM_ACCOUNT)) {
            preparedStatement.setString(1, accountId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    result = resultSet.getInt("balance");
                }
            }
        }
        return result;
    }

    @Override
    public Account getAccountById(Connection connection, String accountId) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_ACCOUNT_BY_ID)) {
            preparedStatement.setString(1, accountId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return getAccountWithoutUser(resultSet);
                }
            }
        }
        return new Account();
    }

    @Override
    public boolean blockAccount(Connection connection, String accountId) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_BLOCK_ACCOUNT)) {
            DBUtil.fillPreparedStatement(
                    preparedStatement,
                    accountId);

            return preparedStatement.executeUpdate() > 0;
        }
    }

    @Override
    public boolean unBlockAccount(Connection connection, String accountId) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_UNBLOCK_ACCOUNT)) {
            DBUtil.fillPreparedStatement(
                    preparedStatement,
                    accountId);

            return preparedStatement.executeUpdate() > 0;
        }
    }

    @Override
    public int getAllUserBalance(Connection connection, String login) throws SQLException {
        int result = 0;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_SUM_BALANCE)) {
            preparedStatement.setString(1, login);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    result = resultSet.getInt(1);
                }
            }
        }
        return result;
    }
}
