package ua.nure.zhazhky.SummaryTask4.db.services;

import ua.nure.zhazhky.SummaryTask4.db.transactions.AccountTransactionService;
import ua.nure.zhazhky.SummaryTask4.db.transactions.CreditCardTransactionService;
import ua.nure.zhazhky.SummaryTask4.db.transactions.PaymentTransactionService;
import ua.nure.zhazhky.SummaryTask4.db.transactions.UserTransactionService;

public interface TransactionServiceFactory {

    UserTransactionService getUserTransactionService();

    CreditCardTransactionService getCreditCardTransactionService();

    AccountTransactionService getAccountTransactionService();

    PaymentTransactionService getPaymentTransactionService();
}
