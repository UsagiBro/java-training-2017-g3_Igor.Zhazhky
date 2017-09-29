package ua.nure.zhazhky.SummaryTask4.db.abstractions;

import ua.nure.zhazhky.SummaryTask4.model.CreditCard;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract class CreditCardDAO{

    public abstract List<CreditCard> getAllCreditCards();

    public abstract boolean createCard(Connection connection, CreditCard creditCard) throws SQLException;

    public abstract List<CreditCard> getCardsByAccount(Connection connection, String accountId) throws SQLException;

    public CreditCard getCard(ResultSet resultSet) throws SQLException {
        CreditCard creditCard = new CreditCard();
        creditCard.setCardId(resultSet.getString("card_id"));
        creditCard.setAccountId(resultSet.getString("account_id"));
        creditCard.setAccountId(resultSet.getString("password"));
        return creditCard;
    }
}
