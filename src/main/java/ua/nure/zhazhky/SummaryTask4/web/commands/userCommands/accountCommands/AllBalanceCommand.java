package ua.nure.zhazhky.SummaryTask4.web.commands.userCommands.accountCommands;

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

public class AllBalanceCommand extends Command {
    private static final Logger LOG = Logger.getLogger(AllBalanceCommand.class);
    private static final Facade FACADE =
            TransactionServiceFacade.getInstance(MySQLTransactionServiceFactory.getInstance());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ApplicationException {
        LOG.debug("Command starts");

        HttpSession session = req.getSession();
        User user =(User) session.getAttribute("user");
        int allBalance = FACADE.getAllUserBalance(user.getLogin());

        session.setAttribute("allBalance", allBalance);


        LOG.debug("Command finished");
        return Paths.CABINET_SERVLET;
    }
}
