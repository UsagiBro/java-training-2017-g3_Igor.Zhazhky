package ua.nure.zhazhky.SummaryTask4.web.commands.userCommands.accountCommands;

import org.apache.log4j.Logger;
import ua.nure.zhazhky.SummaryTask4.db.Facade;
import ua.nure.zhazhky.SummaryTask4.db.TransactionServiceFacade;
import ua.nure.zhazhky.SummaryTask4.db.services.mySQL.MySQLTransactionServiceFactory;
import ua.nure.zhazhky.SummaryTask4.exceptions.ApplicationException;
import ua.nure.zhazhky.SummaryTask4.model.Account;
import ua.nure.zhazhky.SummaryTask4.web.Paths;
import ua.nure.zhazhky.SummaryTask4.web.commands.Command;
import ua.nure.zhazhky.SummaryTask4.web.validators.account.BalanceValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;


public class AddCreditCommand extends Command {

    private static final Logger LOG = Logger.getLogger(AddCreditCommand.class);
    private static final Facade FACADE =
            TransactionServiceFacade.getInstance(MySQLTransactionServiceFactory.getInstance());
    private static final BalanceValidator BALANCE_VALIDATOR = BalanceValidator.getInstance();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ApplicationException {
        LOG.debug("Command starts");

        HttpSession session = req.getSession();
        String accountId = req.getParameter("accountId");
        LOG.trace(String.format("Session attribute: accountId --> %s", accountId));
        String balanceStr = req.getParameter("balance");
        LOG.trace(String.format("Session attribute: balance --> %s", balanceStr));
        BALANCE_VALIDATOR.validate(balanceStr);
        int balance= Integer.parseInt(balanceStr);
        int sum = balance;
        List<Account> accounts =(List<Account>) session.getAttribute("accounts");
        for (Account account : accounts) {
            if (accountId.equals(account.getAccountId())) {
                sum = balance + account.getBalance();
            }
        }
        FACADE.updateAccountSetBalance(sum, accountId);

        for (Account account : accounts) {
            if (accountId.equals(account.getAccountId())) {
                account.setBalance(sum);
            }
        }

        LOG.debug("Command finished");
        return Paths.CABINET_SERVLET;
    }
}
