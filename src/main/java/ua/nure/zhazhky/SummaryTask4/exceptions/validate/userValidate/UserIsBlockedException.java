package ua.nure.zhazhky.SummaryTask4.exceptions.validate.userValidate;

import ua.nure.zhazhky.SummaryTask4.exceptions.validate.ValidateException;

public class UserIsBlockedException extends ValidateException {
    public UserIsBlockedException(String message) {
        super(message);
    }
}
