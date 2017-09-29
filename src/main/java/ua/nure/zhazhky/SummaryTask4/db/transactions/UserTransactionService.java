package ua.nure.zhazhky.SummaryTask4.db.transactions;

import ua.nure.zhazhky.SummaryTask4.exceptions.database.DBException;
import ua.nure.zhazhky.SummaryTask4.model.User;

import java.util.List;

public interface UserTransactionService {

    boolean createUser(User user) throws DBException;

    List<User> getAllUsers() throws DBException;

    User getUserByLoginAndPassword(String login, String password) throws DBException;

    boolean updateUserByLogin(User user, String oldLogin) throws DBException;

    boolean setUserFieldBlockedByUserLogin(boolean blocked, String userLogin) throws DBException;
}
