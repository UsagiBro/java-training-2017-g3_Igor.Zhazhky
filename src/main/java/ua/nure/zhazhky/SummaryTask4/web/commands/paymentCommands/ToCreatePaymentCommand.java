package ua.nure.zhazhky.SummaryTask4.web.commands.paymentCommands;

import org.apache.log4j.Logger;
import ua.nure.zhazhky.SummaryTask4.exceptions.ApplicationException;
import ua.nure.zhazhky.SummaryTask4.web.Paths;
import ua.nure.zhazhky.SummaryTask4.web.commands.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ToCreatePaymentCommand extends Command {

    private static final Logger LOG = Logger.getLogger(ToCreatePaymentCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ApplicationException {
        LOG.debug("Command starts");
        LOG.debug("Command finished");
        return Paths.CREATE_PAYMENT_PAGE;
    }
}
