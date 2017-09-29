package ua.nure.zhazhky.SummaryTask4.exceptions.validate.userValidate;


import ua.nure.zhazhky.SummaryTask4.exceptions.validate.ValidateException;

public class IncorrectLoginException extends ValidateException {

    public IncorrectLoginException(String message) {
        super(message);
    }
}
