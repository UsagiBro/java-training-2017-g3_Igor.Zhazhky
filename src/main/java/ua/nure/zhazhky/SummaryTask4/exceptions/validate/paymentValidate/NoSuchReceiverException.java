package ua.nure.zhazhky.SummaryTask4.exceptions.validate.paymentValidate;

import ua.nure.zhazhky.SummaryTask4.exceptions.validate.ValidateException;

public class NoSuchReceiverException extends ValidateException {
    public NoSuchReceiverException(String message) {
        super(message);
    }
}
