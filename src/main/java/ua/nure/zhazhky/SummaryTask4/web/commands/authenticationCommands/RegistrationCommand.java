package ua.nure.zhazhky.SummaryTask4.web.commands.authenticationCommands;

import org.apache.log4j.Logger;
import ua.nure.zhazhky.SummaryTask4.db.Facade;
import ua.nure.zhazhky.SummaryTask4.db.TransactionServiceFacade;
import ua.nure.zhazhky.SummaryTask4.db.services.mySQL.MySQLTransactionServiceFactory;
import ua.nure.zhazhky.SummaryTask4.exceptions.ApplicationException;
import ua.nure.zhazhky.SummaryTask4.exceptions.Messages;
import ua.nure.zhazhky.SummaryTask4.model.User;
import ua.nure.zhazhky.SummaryTask4.web.Paths;
import ua.nure.zhazhky.SummaryTask4.web.commands.Command;
import ua.nure.zhazhky.SummaryTask4.web.validators.user.ConfirmPasswordValidator;
import ua.nure.zhazhky.SummaryTask4.web.validators.user.UserValidator;
import ua.nure.zhazhky.SummaryTask4.web.webUtils.CommandUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegistrationCommand extends Command {

    private static final Logger LOG = Logger.getLogger(AuthenticationCommand.class);
    private static final Facade FACADE =
            TransactionServiceFacade.getInstance(MySQLTransactionServiceFactory.getInstance());
    private static final UserValidator USER_VALIDATOR = UserValidator.getInstance();
    private static final ConfirmPasswordValidator CONFIRM_PASSWORD_VALIDATOR = ConfirmPasswordValidator.getInstance();

    @Override
    public String execute(
            HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ApplicationException {

        LOG.debug("Command starts");

        User user = CommandUtil.getUserFromRequest(req);
        LOG.trace(String.format("Request parameter: user --> %s", user.toString()));
        USER_VALIDATOR.validate(user);
        String confirmPassword = req.getParameter("password_confirmation");
        CONFIRM_PASSWORD_VALIDATOR.validate(confirmPassword);

        checkCaptcha(req);
        LOG.trace(String.format("User created (true, false) --> %b", FACADE.createUser(user)));

        LOG.debug("Command finished");
        return Paths.AUTHENTICATE_SERVLET;
    }

    private void checkCaptcha(HttpServletRequest req) throws ApplicationException {
        String gRecaptchaResponse = req
                .getParameter("g-recaptcha-response");
        System.out.println(gRecaptchaResponse);
        if (gRecaptchaResponse.isEmpty()) {
            throw new ApplicationException(Messages.MAYBE_YOUR_ROBOT_MESSAGE);
        }
    }
}
