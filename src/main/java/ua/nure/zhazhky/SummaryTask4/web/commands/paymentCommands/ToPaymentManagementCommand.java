package ua.nure.zhazhky.SummaryTask4.web.commands.paymentCommands;

import org.apache.log4j.Logger;
import ua.nure.zhazhky.SummaryTask4.db.Facade;
import ua.nure.zhazhky.SummaryTask4.db.TransactionServiceFacade;
import ua.nure.zhazhky.SummaryTask4.db.services.mySQL.MySQLTransactionServiceFactory;
import ua.nure.zhazhky.SummaryTask4.exceptions.ApplicationException;
import ua.nure.zhazhky.SummaryTask4.model.Payment;
import ua.nure.zhazhky.SummaryTask4.model.User;
import ua.nure.zhazhky.SummaryTask4.web.Paths;
import ua.nure.zhazhky.SummaryTask4.web.commands.Command;
import ua.nure.zhazhky.SummaryTask4.web.commands.userCommands.accountCommands.ToCreateAccountCommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class ToPaymentManagementCommand extends Command {

    private static final Logger LOG = Logger.getLogger(ToCreateAccountCommand.class);

    private static final Facade FACADE =
            TransactionServiceFacade.getInstance(MySQLTransactionServiceFactory.getInstance());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ApplicationException {
        LOG.debug("Command starts");
        HttpSession session = req.getSession();
        User user =(User) session.getAttribute("user");
        List<Payment> payments = FACADE.getPaymentsByUserLogin(user.getLogin());
        session.setAttribute("payments", payments);
        LOG.debug("Command finished");
        return Paths.PAYMENT_MANAGEMENT_PAGE;
    }
}
