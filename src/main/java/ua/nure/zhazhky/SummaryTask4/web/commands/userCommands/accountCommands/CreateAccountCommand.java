package ua.nure.zhazhky.SummaryTask4.web.commands.userCommands.accountCommands;

import org.apache.log4j.Logger;
import ua.nure.zhazhky.SummaryTask4.db.Facade;
import ua.nure.zhazhky.SummaryTask4.db.TransactionServiceFacade;
import ua.nure.zhazhky.SummaryTask4.db.services.mySQL.MySQLTransactionServiceFactory;
import ua.nure.zhazhky.SummaryTask4.exceptions.ApplicationException;
import ua.nure.zhazhky.SummaryTask4.exceptions.database.DBException;
import ua.nure.zhazhky.SummaryTask4.exceptions.validate.ValidateException;
import ua.nure.zhazhky.SummaryTask4.model.Account;
import ua.nure.zhazhky.SummaryTask4.model.User;
import ua.nure.zhazhky.SummaryTask4.web.Paths;
import ua.nure.zhazhky.SummaryTask4.web.commands.Command;
import ua.nure.zhazhky.SummaryTask4.web.validators.account.AccIdValidator;
import ua.nure.zhazhky.SummaryTask4.web.webUtils.CardNumberGenerator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;



public class CreateAccountCommand extends Command {

    private static final Logger LOG = Logger.getLogger(CreateAccountCommand.class);
    private static final Facade FACADE =
            TransactionServiceFacade.getInstance(MySQLTransactionServiceFactory.getInstance());
    private static final AccIdValidator ACC_ID_VALIDATOR = AccIdValidator.getInstance();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ApplicationException {
        LOG.debug("Command starts");

        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        LOG.trace(String.format("Session attribute: user --> %s", user.toString()));

        Account account = getAccount(req, user);
        FACADE.createAccount(account);
        List<Account> accounts = (List<Account>)session.getAttribute("accounts");
        accounts.add(account);

        String cardNumber = CardNumberGenerator.generate("9507", 16);
        session.setAttribute("cardNumber", cardNumber);

        LOG.debug("Command finished");
        return Paths.CREATE_CARD_SERVLET;
    }

    private Account getAccount(HttpServletRequest req, User user) throws ValidateException, DBException {
        String accountId = req.getParameter("accountId");
        ACC_ID_VALIDATOR.validate(accountId);
        LOG.trace(String.format("Request parameter: accountId --> %b", accountId));
        String name = req.getParameter("name");
        LOG.trace(String.format("Request parameter: name --> %b", name));

        Account account = new Account();
        account.setAccountId(accountId);
        account.setName(name);
        account.setUser(user);

        return account;
    }
}
