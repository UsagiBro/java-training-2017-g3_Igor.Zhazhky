package ua.nure.zhazhky.SummaryTask4.web.commands.adminCommands;

import org.apache.log4j.Logger;
import ua.nure.zhazhky.SummaryTask4.exceptions.ApplicationException;
import ua.nure.zhazhky.SummaryTask4.web.Paths;
import ua.nure.zhazhky.SummaryTask4.web.commands.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ToBlockUserCommand extends Command {
    private static final Logger LOG = Logger.getLogger(ToBlockUserCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ApplicationException {
        LOG.debug("Command starts");
        // no op
        LOG.debug("Command finished");
        return Paths.BLOCK_USER_PAGE;
    }
}
