package ua.nure.zhazhky.SummaryTask4.web.commands.authenticationCommands;

import org.apache.log4j.Logger;
import ua.nure.zhazhky.SummaryTask4.exceptions.ApplicationException;
import ua.nure.zhazhky.SummaryTask4.web.Paths;
import ua.nure.zhazhky.SummaryTask4.web.commands.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ExitCommand extends Command {

    private static final Logger LOG = Logger.getLogger(ExitCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ApplicationException {
        LOG.debug("Command starts");

        req.getSession().invalidate();

        LOG.debug("Command finished");
        return Paths.AUTHENTICATE_PAGE;
    }
}
