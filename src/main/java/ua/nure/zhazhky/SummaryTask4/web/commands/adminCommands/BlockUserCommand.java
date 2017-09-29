package ua.nure.zhazhky.SummaryTask4.web.commands.adminCommands;

import org.apache.log4j.Logger;
import ua.nure.zhazhky.SummaryTask4.db.Facade;
import ua.nure.zhazhky.SummaryTask4.db.TransactionServiceFacade;
import ua.nure.zhazhky.SummaryTask4.db.services.mySQL.MySQLTransactionServiceFactory;
import ua.nure.zhazhky.SummaryTask4.exceptions.ApplicationException;
import ua.nure.zhazhky.SummaryTask4.model.User;
import ua.nure.zhazhky.SummaryTask4.web.Paths;
import ua.nure.zhazhky.SummaryTask4.web.commands.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class BlockUserCommand extends Command {
    private static final Logger LOG = Logger.getLogger(BlockUserCommand.class);
    private static final Facade FACADE =
            TransactionServiceFacade.getInstance(MySQLTransactionServiceFactory.getInstance());
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ApplicationException {
        LOG.debug("Command starts");
        String login = req.getParameter("login");

        FACADE.setUserFieldBlockedByUserLogin(true, login);
        HttpSession session = req.getSession();
        List<User> users = (List<User>)session.getAttribute("users");
        for (User user : users) {
            if (login.equals(user.getLogin())) {
                user.setBlocked(true);
            }
        }

        LOG.debug("Command finished");
        return Paths.ADMIN_CABINET_SERVLET;
    }
}
