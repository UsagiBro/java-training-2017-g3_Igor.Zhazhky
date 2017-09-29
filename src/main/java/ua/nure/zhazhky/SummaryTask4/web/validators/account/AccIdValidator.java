package ua.nure.zhazhky.SummaryTask4.web.validators.account;


import ua.nure.zhazhky.SummaryTask4.exceptions.validate.ValidateException;
import ua.nure.zhazhky.SummaryTask4.exceptions.validate.accountValidate.IncorrectAccIdException;
import ua.nure.zhazhky.SummaryTask4.web.validators.Validator;
import ua.nure.zhazhky.SummaryTask4.web.webUtils.ValidatorUtil;

import java.util.regex.Pattern;

public class AccIdValidator implements Validator<String> {

    private static final String REGEX_FOR_ACCOUNT_ID = "(\\d){8}";

    private static final Pattern PATTERN_FOR_ACCOUNT_ID = Pattern.compile(REGEX_FOR_ACCOUNT_ID);

    private static AccIdValidator accIdValidator = new AccIdValidator();

    private AccIdValidator() {
    }

    public static AccIdValidator getInstance(){
        return accIdValidator;
    }

    @Override
    public void validate(String account_id) throws ValidateException {
        String value = String.valueOf(account_id);
        if (value == null || !ValidatorUtil.matchPattern(PATTERN_FOR_ACCOUNT_ID, value)) {
            throw new IncorrectAccIdException("Incorrect account id, " + System.lineSeparator() +
                    "account id must be a 8-digit number");
        }
    }
}
