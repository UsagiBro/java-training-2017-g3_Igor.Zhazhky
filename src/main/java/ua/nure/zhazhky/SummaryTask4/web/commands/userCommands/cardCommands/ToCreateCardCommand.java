package ua.nure.zhazhky.SummaryTask4.web.commands.userCommands.cardCommands;

import org.apache.log4j.Logger;
import ua.nure.zhazhky.SummaryTask4.exceptions.ApplicationException;
import ua.nure.zhazhky.SummaryTask4.web.Paths;
import ua.nure.zhazhky.SummaryTask4.web.commands.Command;
import ua.nure.zhazhky.SummaryTask4.web.commands.userCommands.accountCommands.ToCreateAccountCommand;
import ua.nure.zhazhky.SummaryTask4.web.webUtils.CardNumberGenerator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ToCreateCardCommand extends Command {

    private static final Logger LOG = Logger.getLogger(ToCreateAccountCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ApplicationException {
        LOG.debug("Command starts");
        String cardNumber = CardNumberGenerator.generate("9507", 16);
        HttpSession session = req.getSession();
        session.setAttribute("cardNumber", cardNumber);
        LOG.debug("Command finished");
        return Paths.CREATE_CARD_SERVLET;
    }
}
