package ua.nure.zhazhky.SummaryTask4.web.commands.userCommands.cardCommands;

import org.apache.log4j.Logger;
import ua.nure.zhazhky.SummaryTask4.db.Facade;
import ua.nure.zhazhky.SummaryTask4.db.TransactionServiceFacade;
import ua.nure.zhazhky.SummaryTask4.db.services.mySQL.MySQLTransactionServiceFactory;
import ua.nure.zhazhky.SummaryTask4.exceptions.ApplicationException;
import ua.nure.zhazhky.SummaryTask4.exceptions.validate.ValidateException;
import ua.nure.zhazhky.SummaryTask4.model.CreditCard;
import ua.nure.zhazhky.SummaryTask4.web.Paths;
import ua.nure.zhazhky.SummaryTask4.web.commands.Command;
import ua.nure.zhazhky.SummaryTask4.web.commands.userCommands.accountCommands.CreateAccountCommand;
import ua.nure.zhazhky.SummaryTask4.web.validators.card.CardPINValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;


public class CreateCardCommand extends Command {


    private static final Logger LOG = Logger.getLogger(CreateAccountCommand.class);
    private static final Facade FACADE =
            TransactionServiceFacade.getInstance(MySQLTransactionServiceFactory.getInstance());
    private static final CardPINValidator CARD_PIN_VALIDATOR = CardPINValidator.getInstance();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ApplicationException {
        LOG.debug("Command starts");
        HttpSession session = req.getSession();
        String accountId = req.getParameter("accountId");

        CreditCard creditCard = getCard(req, accountId);
        FACADE.createCreditCard(creditCard);
        List<CreditCard> cards = (List<CreditCard>) session.getAttribute("cards");
        cards.add(creditCard);

        LOG.debug("Command finished");
        return Paths.CABINET_SERVLET;
    }

    private CreditCard getCard(HttpServletRequest req, String accountId) throws ValidateException {
        String cardId = req.getParameter("cardId");
        String password = req.getParameter("password");
        CARD_PIN_VALIDATOR.validate(password);
        LOG.trace(String.format("Request parameter: cardId --> %b", cardId));
        LOG.trace(String.format("Request parameter: accountId --> %b", accountId));
        CreditCard creditCard = new CreditCard();
        creditCard.setCardId(cardId);
        creditCard.setAccountId(accountId);
        creditCard.setPassword(password);

        return creditCard;
    }
}
