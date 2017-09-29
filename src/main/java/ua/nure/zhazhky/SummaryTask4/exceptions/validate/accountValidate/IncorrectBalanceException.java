package ua.nure.zhazhky.SummaryTask4.exceptions.validate.accountValidate;

import ua.nure.zhazhky.SummaryTask4.exceptions.validate.ValidateException;

public class IncorrectBalanceException extends ValidateException {

    public IncorrectBalanceException(String message) {
        super(message);
    }
}