package ua.nure.zhazhky.SummaryTask4.web.commands.paymentCommands;

import org.apache.log4j.Logger;
import ua.nure.zhazhky.SummaryTask4.db.Facade;
import ua.nure.zhazhky.SummaryTask4.db.TransactionServiceFacade;
import ua.nure.zhazhky.SummaryTask4.db.services.mySQL.MySQLTransactionServiceFactory;
import ua.nure.zhazhky.SummaryTask4.exceptions.ApplicationException;
import ua.nure.zhazhky.SummaryTask4.model.Account;
import ua.nure.zhazhky.SummaryTask4.model.Cheque;
import ua.nure.zhazhky.SummaryTask4.model.User;
import ua.nure.zhazhky.SummaryTask4.web.Paths;
import ua.nure.zhazhky.SummaryTask4.web.commands.Command;
import ua.nure.zhazhky.SummaryTask4.web.validators.account.BalanceValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;


public class PaymentConfirmCommand extends Command {
    private static final Logger LOG = Logger.getLogger(CreatePaymentCommand.class);
    private static final Facade FACADE =
            TransactionServiceFacade.getInstance(MySQLTransactionServiceFactory.getInstance());
    private static final BalanceValidator BALANCE_VALIDATOR = BalanceValidator.getInstance();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ApplicationException {
        LOG.debug("Command starts");
        HttpSession session = req.getSession();
        Cheque cheque =(Cheque) session.getAttribute("lastCheque");
        FACADE.updatePaymentSetStatusAndAccountsSetBalance(cheque);

        User user = (User) session.getAttribute("user");
        List<Account> accounts = FACADE.getAccountsByUserLogin(user.getLogin());
        session.setAttribute("accounts", accounts);
        session.removeAttribute("lastCheque");

        LOG.debug("Command finished");
        return Paths.CABINET_SERVLET;
    }
}
