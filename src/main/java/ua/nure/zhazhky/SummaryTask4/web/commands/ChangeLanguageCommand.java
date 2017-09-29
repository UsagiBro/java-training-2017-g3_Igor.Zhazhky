package ua.nure.zhazhky.SummaryTask4.web.commands;

import org.apache.log4j.Logger;
import ua.nure.zhazhky.SummaryTask4.exceptions.ApplicationException;
import ua.nure.zhazhky.SummaryTask4.web.Paths;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ChangeLanguageCommand extends Command {

    private static final Logger LOG = Logger.getLogger(ChangeLanguageCommand.class);


    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ApplicationException {
        LOG.debug("Command starts");

        String language = req.getParameter("language");
        LOG.trace(String.format("Request parameter: language --> %s", language));
        req.getSession().setAttribute("language", language);

        LOG.debug("Command finished");
        return Paths.CABINET_SERVLET;
    }
}
