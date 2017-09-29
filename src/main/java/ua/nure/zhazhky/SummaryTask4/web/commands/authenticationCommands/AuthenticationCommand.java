package ua.nure.zhazhky.SummaryTask4.web.commands.authenticationCommands;

import org.apache.log4j.Logger;
import ua.nure.zhazhky.SummaryTask4.db.Facade;
import ua.nure.zhazhky.SummaryTask4.db.TransactionServiceFacade;
import ua.nure.zhazhky.SummaryTask4.db.services.mySQL.MySQLTransactionServiceFactory;
import ua.nure.zhazhky.SummaryTask4.exceptions.ApplicationException;
import ua.nure.zhazhky.SummaryTask4.exceptions.Messages;
import ua.nure.zhazhky.SummaryTask4.exceptions.validate.userValidate.UserIsBlockedException;
import ua.nure.zhazhky.SummaryTask4.model.Account;
import ua.nure.zhazhky.SummaryTask4.model.CreditCard;
import ua.nure.zhazhky.SummaryTask4.model.Payment;
import ua.nure.zhazhky.SummaryTask4.model.User;
import ua.nure.zhazhky.SummaryTask4.web.Paths;
import ua.nure.zhazhky.SummaryTask4.web.commands.Command;
import ua.nure.zhazhky.SummaryTask4.web.validators.user.LoginValidator;
import ua.nure.zhazhky.SummaryTask4.web.validators.user.PasswordValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AuthenticationCommand extends Command {

    private static final Logger LOG = Logger.getLogger(AuthenticationCommand.class);
    private static final Facade FACADE =
            TransactionServiceFacade.getInstance(MySQLTransactionServiceFactory.getInstance());
    private static final LoginValidator LOGIN_VALIDATOR = LoginValidator.getInstance();
    private static final PasswordValidator PASSWORD_VALIDATOR = PasswordValidator.getInstance();

    @Override
    public String execute(
            HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ApplicationException {
        LOG.debug("Command starts");

        String login = req.getParameter("login");
        LOGIN_VALIDATOR.validate(login);
        LOG.trace(String.format("Request parameter: login --> %s", login));

        String password = req.getParameter("password");
        PASSWORD_VALIDATOR.validate(password);

        User user = FACADE.getUserByLoginAndPassword(login, password);
        checkUserFilled(user);

        HttpSession session = req.getSession();
        session.setAttribute("user", user);

        List<Account> accounts = FACADE.getAccountsByUserLogin(user.getLogin());
        session.setAttribute("accounts", accounts);

        List<CreditCard> cards = new ArrayList<>();
        for (Account account : accounts) {
            List<CreditCard> temp = FACADE.getCardsByAccount(account.getAccountId());
            cards.addAll(temp);
        }
        session.setAttribute("cards", cards);

        List<Payment> payments = FACADE.getPaymentsByUserLogin(user.getLogin());
        session.setAttribute("payments", payments);

        if (user.isBlocked()) {
            throw new UserIsBlockedException(Messages.ERR_USER_IS_BLOCKED);
        }

        LOG.debug("Command finished");
        return Paths.CABINET_SERVLET;
    }

    private void checkUserFilled(User user) throws ApplicationException {
        if (!user.isFilled()) {
            throw new ApplicationException(Messages.USER_NOT_EXIST_MESSAGE);
        }
    }
}
