package ua.nure.zhazhky.SummaryTask4.web.commands.userCommands.accountCommands;

import org.apache.log4j.Logger;
import ua.nure.zhazhky.SummaryTask4.exceptions.ApplicationException;
import ua.nure.zhazhky.SummaryTask4.model.Account;
import ua.nure.zhazhky.SummaryTask4.web.Paths;
import ua.nure.zhazhky.SummaryTask4.web.commands.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class SortAccountsByBalanceCommand extends Command {
    private static final Logger LOG = Logger.getLogger(SortAccountsByBalanceCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ApplicationException {
        LOG.debug("Command starts");
        HttpSession session = req.getSession();
        List<Account> accounts = (List<Account>) session.getAttribute("accounts");
        Collections.sort(accounts, new Comparator<Account>() {
            @Override
            public int compare(Account a1, Account a2) {
                return -(a1.getBalance() - a2.getBalance());
            }
        });
        LOG.debug("Command finished");
        return Paths.CABINET_SERVLET;
    }
}
