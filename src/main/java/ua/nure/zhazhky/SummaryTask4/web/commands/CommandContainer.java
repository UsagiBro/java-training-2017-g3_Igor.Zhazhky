package ua.nure.zhazhky.SummaryTask4.web.commands;

import org.apache.log4j.Logger;
import ua.nure.zhazhky.SummaryTask4.web.commands.adminCommands.*;
import ua.nure.zhazhky.SummaryTask4.web.commands.authenticationCommands.AuthenticationCommand;
import ua.nure.zhazhky.SummaryTask4.web.commands.authenticationCommands.ExitCommand;
import ua.nure.zhazhky.SummaryTask4.web.commands.authenticationCommands.RegistrationCommand;
import ua.nure.zhazhky.SummaryTask4.web.commands.paymentCommands.*;
import ua.nure.zhazhky.SummaryTask4.web.commands.userCommands.ToCabinetCommand;
import ua.nure.zhazhky.SummaryTask4.web.commands.userCommands.ToProfileCommand;
import ua.nure.zhazhky.SummaryTask4.web.commands.userCommands.ToUpdateUserCommand;
import ua.nure.zhazhky.SummaryTask4.web.commands.userCommands.UpdateUserCommand;
import ua.nure.zhazhky.SummaryTask4.web.commands.userCommands.accountCommands.*;
import ua.nure.zhazhky.SummaryTask4.web.commands.userCommands.cardCommands.CreateCardCommand;
import ua.nure.zhazhky.SummaryTask4.web.commands.userCommands.cardCommands.ToCreateCardCommand;

import java.util.HashMap;
import java.util.Map;

public class CommandContainer {

    private static final Logger LOG = Logger.getLogger(CommandContainer.class);
    private static Map<String, Command> commands = new HashMap<>();

    static {

        //common
        commands.put("authenticate", new AuthenticationCommand());
        commands.put("registration", new RegistrationCommand());
        commands.put("toCabinet", new ToCabinetCommand());
        commands.put("exit", new ExitCommand());
        commands.put("changeLanguage", new ChangeLanguageCommand());
        commands.put("noCommand", new NoCommand());

        //user
        commands.put("toProfile", new ToProfileCommand());
        commands.put("toUpdateUser", new ToUpdateUserCommand());
        commands.put("updateUser", new UpdateUserCommand());
        commands.put("toCreateAccount", new ToCreateAccountCommand());
        commands.put("createAccount", new CreateAccountCommand());
        commands.put("createCard", new CreateCardCommand());
        commands.put("toCreateCard", new ToCreateCardCommand());
        commands.put("addCredit", new AddCreditCommand());
        commands.put("toAddCredit", new ToAddCreditCommand());
        commands.put("sortAById", new SortAccountsByIdCommand());
        commands.put("sortAByName", new SortAccountsByNameCommand());
        commands.put("sortAByBalance", new SortAccountsByBalanceCommand());
        commands.put("toBlockAccount", new ToBlockAccountCommand());
        commands.put("toUnblockAccountRequest", new ToUnblockAccountRequestCommand());
        commands.put("blockAccount", new BlockAccountCommand());
        commands.put("unblockAccountRequestCommand", new UnblockAccountRequestCommand());
        commands.put("allBalance", new AllBalanceCommand());

        //admin
        commands.put("toAdminCabinet", new ToAdminCabinetCommand());
        commands.put("toUnblockAccount", new ToUnblockAccountCommand());
        commands.put("unblockAccount", new UnblockAccountCommand());
        commands.put("blockUser", new BlockUserCommand());
        commands.put("toBlockUser", new ToBlockUserCommand());
        commands.put("unblockUser", new UnblockUserCommand());
        commands.put("toUnblockUser", new ToUnblockUserCommand());

        //payment
        commands.put("createPayment", new CreatePaymentCommand());
        commands.put("paymentConfirm", new PaymentConfirmCommand());
        commands.put("toCreatePayment", new ToCreatePaymentCommand());
        commands.put("toPaymentManagement", new ToPaymentManagementCommand());
        commands.put("sortPByDateUp", new SortPaymentsByDateUpCommand());
        commands.put("sortPByDateDown", new SortPaymentsByDateDownCommand());
        commands.put("toDeleteUnsentPayments", new ToDeleteUnsentPaymentsCommand());
        commands.put("deleteUnsentPayments", new DeleteUnsentPaymentsCommand());

        LOG.debug(String.format("All commands --> %s", commands.keySet()));
    }

    public static Command getCommand(String commandName) {
        if (commands.containsKey(commandName)) {
            return commands.get(commandName);
        }
        LOG.trace(String.format("Command not found, name --> %s", commandName));
        return commands.get("noCommand");
    }
}
