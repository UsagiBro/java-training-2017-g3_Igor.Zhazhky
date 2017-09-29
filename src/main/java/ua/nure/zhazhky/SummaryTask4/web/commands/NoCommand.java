package ua.nure.zhazhky.SummaryTask4.web.commands;

import org.apache.log4j.Logger;
import ua.nure.zhazhky.SummaryTask4.web.Paths;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NoCommand extends Command {

    private static final Logger LOG = Logger.getLogger(NoCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        LOG.debug("Command starts");

        String errorMessage = "I don't know such command(";
        req.getSession().setAttribute("error", errorMessage);
        LOG.error(String.format("Set the request attribute: errorMessage --> %s", errorMessage));

        LOG.debug("Command finished");
        return Paths.ERROR_SERVLET;
    }
}
