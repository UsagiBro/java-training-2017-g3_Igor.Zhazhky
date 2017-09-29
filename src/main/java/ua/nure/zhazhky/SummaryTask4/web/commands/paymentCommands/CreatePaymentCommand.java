package ua.nure.zhazhky.SummaryTask4.web.commands.paymentCommands;

import org.apache.log4j.Logger;
import ua.nure.zhazhky.SummaryTask4.db.Facade;
import ua.nure.zhazhky.SummaryTask4.db.TransactionServiceFacade;
import ua.nure.zhazhky.SummaryTask4.db.services.mySQL.MySQLTransactionServiceFactory;
import ua.nure.zhazhky.SummaryTask4.exceptions.ApplicationException;
import ua.nure.zhazhky.SummaryTask4.exceptions.Messages;
import ua.nure.zhazhky.SummaryTask4.exceptions.database.DBException;
import ua.nure.zhazhky.SummaryTask4.exceptions.validate.ValidateException;
import ua.nure.zhazhky.SummaryTask4.exceptions.validate.paymentValidate.NoSuchReceiverException;
import ua.nure.zhazhky.SummaryTask4.exceptions.validate.paymentValidate.NotEnoughBalanceException;
import ua.nure.zhazhky.SummaryTask4.model.Account;
import ua.nure.zhazhky.SummaryTask4.model.Cheque;
import ua.nure.zhazhky.SummaryTask4.model.Payment;
import ua.nure.zhazhky.SummaryTask4.model.User;
import ua.nure.zhazhky.SummaryTask4.web.Paths;
import ua.nure.zhazhky.SummaryTask4.web.commands.Command;
import ua.nure.zhazhky.SummaryTask4.web.validators.account.AccIdValidator;
import ua.nure.zhazhky.SummaryTask4.web.validators.account.BalanceValidator;
import ua.nure.zhazhky.SummaryTask4.web.webUtils.CommandUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class CreatePaymentCommand extends Command {

    private static final Logger LOG = Logger.getLogger(CreatePaymentCommand.class);
    private static final Facade FACADE =
            TransactionServiceFacade.getInstance(MySQLTransactionServiceFactory.getInstance());
    private static final AccIdValidator ACC_ID_VALIDATOR = AccIdValidator.getInstance();
    private static final BalanceValidator BALANCE_VALIDATOR = BalanceValidator.getInstance();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ApplicationException {
        LOG.debug("Command starts");

        String date = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
        LOG.trace(String.format("Request parameter: accountId --> %b", date));

        String sender = req.getParameter("sender");
        ACC_ID_VALIDATOR.validate(sender);
        LOG.trace(String.format("Request parameter: name --> %b", sender));


        String receiver = req.getParameter("receiver");
        ACC_ID_VALIDATOR.validate(receiver);
        LOG.trace(String.format("Request parameter: name --> %b", receiver));

        String balanceStr = req.getParameter("balance");
        BALANCE_VALIDATOR.validate(balanceStr);
        LOG.trace(String.format("Request parameter: balance --> %b", balanceStr));
        int balance = Integer.valueOf(balanceStr);


        Payment payment = getPayment(req, date, balance, sender, receiver);
        HttpSession session = req.getSession();
        User user =(User) session.getAttribute("user");
        Cheque cheque = calculateSum(balance, sender, receiver);
        cheque.setDate(date);
        session.setAttribute("lastCheque", cheque);

        FACADE.createPayment(payment, user.getLogin());
        List<Payment> payments = (List<Payment>) session.getAttribute("payments");
        payments.add(payment);


        LOG.debug("Command finished");
        return Paths.PAYMENT_CONFIRM_SERVLET;
    }

    private Payment getPayment(HttpServletRequest req, String date, int balance, String sender, String receiver)
            throws ValidateException, DBException {
        Payment payment = new Payment();
        payment.setDate(date);
        payment.setBalance(balance);
        payment.setAccountSender(sender);
        payment.setAccountReceiver(receiver);

        return payment;
    }

    /**
     * Generates the cheque from payment values
     *
     * @param balance   Value of a payment sum that will be sent.
     * @param sender    Account id that will send payment.
     * @param receiver  Account id that will receive payment.
     * @return Cheque that will be used in a transaction.
     */
    private Cheque calculateSum(int balance, String sender, String receiver)
            throws DBException, ValidateException {
        Account checkAccount = FACADE.getAccountById(receiver);
        if (checkAccount.getAccountId() == null || checkAccount.isBlocked()) {
            LOG.error(Messages.ERR_RECEIVER_NOT_EXIST);
            throw new NoSuchReceiverException(Messages.ERR_RECEIVER_NOT_EXIST);
        }
        int senderBalance = FACADE.getBalanceByAccountId(sender);
        if (senderBalance - balance < 0) {
            LOG.error(Messages.ERR_NOT_ENOUGH_BALANCE);
            throw new NotEnoughBalanceException(Messages.ERR_NOT_ENOUGH_BALANCE);
        }
        senderBalance = senderBalance - balance;
        int receiverBalance = FACADE.getBalanceByAccountId(receiver) + balance;
        return CommandUtil.getChequeFromRequest(senderBalance, receiverBalance, sender, receiver, balance);
    }
}
