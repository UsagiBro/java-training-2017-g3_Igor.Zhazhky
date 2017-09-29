package ua.nure.zhazhky.SummaryTask4.web.commands.userCommands.accountCommands;

import org.apache.log4j.Logger;
import ua.nure.zhazhky.SummaryTask4.db.Facade;
import ua.nure.zhazhky.SummaryTask4.db.TransactionServiceFacade;
import ua.nure.zhazhky.SummaryTask4.db.services.mySQL.MySQLTransactionServiceFactory;
import ua.nure.zhazhky.SummaryTask4.exceptions.ApplicationException;
import ua.nure.zhazhky.SummaryTask4.model.Account;
import ua.nure.zhazhky.SummaryTask4.web.Paths;
import ua.nure.zhazhky.SummaryTask4.web.commands.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class BlockAccountCommand extends Command {

    private static final Logger LOG = Logger.getLogger(CreateAccountCommand.class);
    private static final Facade FACADE =
            TransactionServiceFacade.getInstance(MySQLTransactionServiceFactory.getInstance());
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ApplicationException {
        LOG.debug("Command starts");
        String accountId = req.getParameter("accountId");

        FACADE.blockAccount(accountId);
        HttpSession session = req.getSession();
        List<Account> accounts = (List<Account>)session.getAttribute("accounts");
        for (Account account : accounts) {
            if (accountId.equals(account.getAccountId())) {
                account.setBlocked(true);
            }
        }

        LOG.debug("Command finished");
        return Paths.CABINET_SERVLET;
    }
}
