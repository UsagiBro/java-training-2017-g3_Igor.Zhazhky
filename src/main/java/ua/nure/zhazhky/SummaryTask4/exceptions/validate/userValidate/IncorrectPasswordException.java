package ua.nure.zhazhky.SummaryTask4.exceptions.validate.userValidate;

import ua.nure.zhazhky.SummaryTask4.exceptions.validate.ValidateException;

public class IncorrectPasswordException extends ValidateException {

    public IncorrectPasswordException(String message) {
        super(message);
    }

}
