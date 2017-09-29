package ua.nure.zhazhky.SummaryTask4.web.validators.account;

import ua.nure.zhazhky.SummaryTask4.exceptions.validate.ValidateException;
import ua.nure.zhazhky.SummaryTask4.exceptions.validate.accountValidate.IncorrectBalanceException;
import ua.nure.zhazhky.SummaryTask4.web.validators.Validator;
import ua.nure.zhazhky.SummaryTask4.web.webUtils.ValidatorUtil;

import java.util.regex.Pattern;

public class BalanceValidator implements Validator<String> {

    private static final String REGEX_FOR_BALANCE = "(\\d)+";
    private static final String REGEX_FOR_BALANCE_LENGHT = "(\\d){1,6}";

    private static final Pattern PATTERN_FOR_BALANCE = Pattern.compile(REGEX_FOR_BALANCE);
    private static final Pattern PATTERN_FOR_BALANCE_LENGTH = Pattern.compile(REGEX_FOR_BALANCE_LENGHT);

    private static BalanceValidator balanceValidator = new BalanceValidator();

    private BalanceValidator() {
    }

    public static BalanceValidator getInstance(){
        return balanceValidator;
    }

    @Override
    public void validate(String balance) throws ValidateException {
        String value = String.valueOf(balance);
        if (value == null || !ValidatorUtil.matchPattern(PATTERN_FOR_BALANCE, value)) {
            throw new IncorrectBalanceException("Incorrect sum for balance, " + System.lineSeparator() +
                    "value must be an integer!");
        }
        if (!ValidatorUtil.matchPattern(PATTERN_FOR_BALANCE_LENGTH, value)) {
            throw new IncorrectBalanceException("Incorrect sum for balance, " + System.lineSeparator() +
                    "you can't deposit million and more.");
        }
    }
}
