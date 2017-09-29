package ua.nure.zhazhky.SummaryTask4.db.services.mySQL.mysqlDAO;

import ua.nure.zhazhky.SummaryTask4.db.abstractions.CreditCardDAO;
import ua.nure.zhazhky.SummaryTask4.db.dbUtils.DBUtil;
import ua.nure.zhazhky.SummaryTask4.model.CreditCard;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySQLCreditCardDAO extends CreditCardDAO {

    private static final String SQL_INSERT_INTO_CARDS = "INSERT INTO credit_cards VALUES (?, ?, ?)";
    private static final String SQL_SELECT_FROM_CARDS_BY_ACCOUNT_ID = "SELECT * FROM credit_cards WHERE account_id = ?";
    private static final String SQL_SELECT_FROM_CARDS_BY_USER_LOGIN =
            "SELECT * FROM cards WHERE account_id IN (SELECT * FROM accounts WHERE user_login=?)";
    private static MySQLCreditCardDAO mySQLCreditCardDAO;

    private MySQLCreditCardDAO() {
    }

    public static MySQLCreditCardDAO getInstance() {
        if (mySQLCreditCardDAO == null) {
            synchronized (MySQLUserDAO.class) {
                if (mySQLCreditCardDAO == null) {
                    mySQLCreditCardDAO = new MySQLCreditCardDAO();
                }
            }
        }
        return mySQLCreditCardDAO;
    }

    @Override
    public List<CreditCard> getAllCreditCards() {
        return null;
    }

    @Override
    public boolean createCard(Connection connection, CreditCard creditCard) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_INTO_CARDS)) {
            DBUtil.fillPreparedStatement(
                    preparedStatement,
                    creditCard.getCardId(),
                    creditCard.getAccountId(),
                    creditCard.getPassword());
            return preparedStatement.executeUpdate() > 0;
        }
    }

    @Override
    public List<CreditCard> getCardsByAccount(Connection connection, String accountId) throws SQLException {
        List<CreditCard> list = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_FROM_CARDS_BY_ACCOUNT_ID)) {
            preparedStatement.setString(1, accountId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    list.add(getCard(resultSet));
                }
            }
        }
        return list;
    }

}
