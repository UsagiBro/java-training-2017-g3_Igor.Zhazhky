package ua.nure.zhazhky.SummaryTask4.web.commands.userCommands;

import org.apache.log4j.Logger;
import ua.nure.zhazhky.SummaryTask4.db.Facade;
import ua.nure.zhazhky.SummaryTask4.db.TransactionServiceFacade;
import ua.nure.zhazhky.SummaryTask4.db.services.mySQL.MySQLTransactionServiceFactory;
import ua.nure.zhazhky.SummaryTask4.exceptions.ApplicationException;
import ua.nure.zhazhky.SummaryTask4.model.User;
import ua.nure.zhazhky.SummaryTask4.web.Paths;
import ua.nure.zhazhky.SummaryTask4.web.commands.Command;
import ua.nure.zhazhky.SummaryTask4.web.validators.user.FullNameValidator;
import ua.nure.zhazhky.SummaryTask4.web.validators.user.LoginValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UpdateUserCommand extends Command {

    private static final Logger LOG = Logger.getLogger(UpdateUserCommand.class);
    private static final Facade FACADE =
            TransactionServiceFacade.getInstance(MySQLTransactionServiceFactory.getInstance());
    private static final LoginValidator LOGIN_VALIDATOR = LoginValidator.getInstance();
    private static final FullNameValidator FULL_NAME_VALIDATOR = FullNameValidator.getInstance();

    @Override
    public String execute(
            HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ApplicationException {

        LOG.debug("Command starts");

        HttpSession session = req.getSession();
        User user =(User) session.getAttribute("user");
        String oldLogin = user.getLogin();

        String login = req.getParameter("login");
        LOGIN_VALIDATOR.validate(login);
        LOG.trace(String.format("Request parameter: login --> %s", login));

        String fullName = req.getParameter("fullname");
        FULL_NAME_VALIDATOR.validate(fullName);

        user.setFullName(fullName);
        user.setLogin(login);
        LOG.trace(String.format("User created (true, false) --> %b", FACADE.updateUserByLogin(user, oldLogin)));

        LOG.debug("Command finished");
        return Paths.PROFILE_SERVLET;
    }

}
