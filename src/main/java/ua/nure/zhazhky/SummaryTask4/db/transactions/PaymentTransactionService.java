package ua.nure.zhazhky.SummaryTask4.db.transactions;

import ua.nure.zhazhky.SummaryTask4.exceptions.database.DBException;
import ua.nure.zhazhky.SummaryTask4.model.Cheque;
import ua.nure.zhazhky.SummaryTask4.model.Payment;

import java.util.List;

public interface PaymentTransactionService {

    boolean createPayment(Payment payment, String userLogin) throws DBException;

    boolean updatePaymentSetStatusAndAccountsSetBalance(Cheque cheque) throws DBException;

    List<Payment> getPaymentsByUserLogin(String userLogin) throws DBException;

    boolean deletePreparedPayments(String login) throws DBException;
}
