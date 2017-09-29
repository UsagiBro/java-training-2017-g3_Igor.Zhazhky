package ua.nure.zhazhky.SummaryTask4.web.webUtils;

import ua.nure.zhazhky.SummaryTask4.model.Cheque;
import ua.nure.zhazhky.SummaryTask4.model.User;

import javax.servlet.http.HttpServletRequest;

public final class CommandUtil {

    private CommandUtil() {}

    public static User getUserFromRequest(HttpServletRequest req) {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String fullName = req.getParameter("fullname");

        User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        user.setFullName(fullName);
        return user;
    }

    public static Cheque getChequeFromRequest(
            int senderSum, int receiverSum, String senderId, String receiverId, int balance) {
        Cheque cheque = new Cheque();
        cheque.setBalance(balance);
        cheque.setReceiverId(receiverId);
        cheque.setSenderId(senderId);
        cheque.setReceiverSum(receiverSum);
        cheque.setSenderSum(senderSum);
        return cheque;
    }
}
