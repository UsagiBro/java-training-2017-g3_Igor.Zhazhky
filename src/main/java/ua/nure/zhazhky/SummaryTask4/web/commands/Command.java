package ua.nure.zhazhky.SummaryTask4.web.commands;

import ua.nure.zhazhky.SummaryTask4.exceptions.ApplicationException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class Command {

    public abstract String execute(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ApplicationException;

    @Override
    public final String toString() {
        return getClass().getSimpleName();
    }

}
