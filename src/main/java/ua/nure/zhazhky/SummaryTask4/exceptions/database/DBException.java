package ua.nure.zhazhky.SummaryTask4.exceptions.database;

import ua.nure.zhazhky.SummaryTask4.exceptions.ApplicationException;

public class DBException extends ApplicationException {

    public DBException() {
        super();
    }

    public DBException(String message, Throwable cause) {
        super(message, cause);
    }
}
