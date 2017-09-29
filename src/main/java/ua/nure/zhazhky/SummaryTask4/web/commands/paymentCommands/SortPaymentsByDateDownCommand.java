package ua.nure.zhazhky.SummaryTask4.web.commands.paymentCommands;

import org.apache.log4j.Logger;
import ua.nure.zhazhky.SummaryTask4.exceptions.ApplicationException;
import ua.nure.zhazhky.SummaryTask4.model.Payment;
import ua.nure.zhazhky.SummaryTask4.web.Paths;
import ua.nure.zhazhky.SummaryTask4.web.commands.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortPaymentsByDateDownCommand extends Command {

    private static final Logger LOG = Logger.getLogger(SortPaymentsByDateDownCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ApplicationException {
        LOG.debug("Command starts");
        HttpSession session = req.getSession();
        List<Payment> payments = (List<Payment>) session.getAttribute("payments");
        Collections.sort(payments, new Comparator<Payment>() {
            @Override
            public int compare(Payment p1, Payment p2) {
                return String.valueOf(p1.getDate()).compareTo(String.valueOf(p2.getDate()));
            }
        });
        LOG.debug("Command finished");
        return Paths.PAYMENT_MANAGEMENT_SERVLET;
    }
}
