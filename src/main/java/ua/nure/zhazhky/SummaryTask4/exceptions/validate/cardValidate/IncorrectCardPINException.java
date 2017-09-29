package ua.nure.zhazhky.SummaryTask4.exceptions.validate.cardValidate;

import ua.nure.zhazhky.SummaryTask4.exceptions.validate.ValidateException;

public class IncorrectCardPINException extends ValidateException {

    public IncorrectCardPINException(String message) {
        super(message);
    }
}
