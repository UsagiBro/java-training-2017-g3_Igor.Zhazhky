package ua.nure.zhazhky.SummaryTask4.db.services.mySQL.mysqlDAO;

import org.apache.commons.codec.digest.DigestUtils;
import ua.nure.zhazhky.SummaryTask4.db.abstractions.UserDAO;
import ua.nure.zhazhky.SummaryTask4.db.dbUtils.DBUtil;
import ua.nure.zhazhky.SummaryTask4.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySQLUserDAO extends UserDAO {

    private static final String SQL_INSERT_USER = "INSERT INTO users VALUES (?, ?, ?, DEFAULT, DEFAULT)";
    private static final String SQL_GET_USER_BY_LOGIN_AND_PASSWORD =
            "SELECT * FROM users WHERE login = ? AND password = ?";
    private static final String SQL_UPDATE_USER =
            "UPDATE users SET login = ?, fullname = ? WHERE login = ?";
    private static final String SQL_GET_USERS = "SELECT * FROM  users WHERE role_id = 2";
    private static final String SQL_SET_USER_BLOCKED = "UPDATE users SET blocked = ? WHERE login = ?";

    private static MySQLUserDAO mySQLUserDAO;

    private MySQLUserDAO() {
    }

    public static MySQLUserDAO getInstance() {
        if (mySQLUserDAO == null) {
            synchronized (MySQLUserDAO.class) {
                if (mySQLUserDAO == null) {
                    mySQLUserDAO = new MySQLUserDAO();
                }
            }
        }
        return mySQLUserDAO;
    }

    @Override
    public boolean createUser(Connection connection, User user) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_USER)) {
            DBUtil.fillPreparedStatement(preparedStatement,
                    user.getLogin(),
                    DigestUtils.md5Hex(user.getPassword()),
                    user.getFullName());
            return preparedStatement.executeUpdate() > 0;
        }
    }

    @Override
    public List<User> getAllUsers(Connection connection) throws SQLException {
        List<User> users = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_USERS)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    users.add(getUser(resultSet));
                }
            }
        }
        return users;
    }

    @Override
    public User getUserByLoginAndPassword(Connection connection, String login, String password) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_USER_BY_LOGIN_AND_PASSWORD)) {
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, DigestUtils.md5Hex(password));
            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                if (resultSet.next()) {
                    return getUser(resultSet);
                }
            }
        }
        return new User();
    }

    @Override
    public boolean updateUser(Connection connection, User user, String oldLogin) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_USER)) {
            DBUtil.fillPreparedStatement(preparedStatement,
                    user.getLogin(),
                    user.getFullName(),
                    oldLogin);
            return preparedStatement.executeUpdate() > 0;
        }
    }

    @Override
    public boolean setUserFieldBlockedByUserLogin(Connection connection, boolean blocked, String login) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SET_USER_BLOCKED)) {
            DBUtil.fillPreparedStatement(preparedStatement,
                    blocked,
                    login);
            return preparedStatement.executeUpdate() > 0;
        }
    }
}
