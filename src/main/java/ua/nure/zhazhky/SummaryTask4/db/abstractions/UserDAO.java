package ua.nure.zhazhky.SummaryTask4.db.abstractions;


import ua.nure.zhazhky.SummaryTask4.model.Role;
import ua.nure.zhazhky.SummaryTask4.model.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract class UserDAO {

    public abstract boolean createUser(Connection connection, User user) throws SQLException;

    public abstract List<User> getAllUsers(Connection connection) throws SQLException;

    public abstract User getUserByLoginAndPassword(Connection connection, String login, String password) throws SQLException;

    public abstract boolean updateUser(Connection connection, User user, String oldLogin) throws SQLException;

    public abstract boolean setUserFieldBlockedByUserLogin(Connection connection, boolean blocked, String login) throws SQLException;

    public User getUser(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setLogin(resultSet.getString("login"));
        user.setPassword(resultSet.getString("password"));
        user.setFullName(resultSet.getString("fullname"));
        user.setRole(Role.getRoleById(resultSet.getInt("role_id")));
        user.setBlocked(resultSet.getBoolean("blocked"));
        return user;
    }
}
