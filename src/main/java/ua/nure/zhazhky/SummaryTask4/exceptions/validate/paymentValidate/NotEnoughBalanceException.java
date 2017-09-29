package ua.nure.zhazhky.SummaryTask4.exceptions.validate.paymentValidate;

import ua.nure.zhazhky.SummaryTask4.exceptions.validate.ValidateException;

public class NotEnoughBalanceException extends ValidateException {

    public NotEnoughBalanceException(String message) {
        super(message);
    }
}
