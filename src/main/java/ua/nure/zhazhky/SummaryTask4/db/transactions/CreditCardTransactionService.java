package ua.nure.zhazhky.SummaryTask4.db.transactions;

import ua.nure.zhazhky.SummaryTask4.exceptions.database.DBException;
import ua.nure.zhazhky.SummaryTask4.model.CreditCard;

import java.util.List;

public interface CreditCardTransactionService {

    boolean createCreditCard(CreditCard creditCard) throws DBException;

    List<CreditCard> getCardsByAccount(String accountId) throws DBException;
}
