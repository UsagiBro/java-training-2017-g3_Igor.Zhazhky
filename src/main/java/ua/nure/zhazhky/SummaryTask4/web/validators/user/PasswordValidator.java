package ua.nure.zhazhky.SummaryTask4.web.validators.user;

import ua.nure.zhazhky.SummaryTask4.exceptions.validate.ValidateException;
import ua.nure.zhazhky.SummaryTask4.exceptions.validate.userValidate.IncorrectPasswordException;
import ua.nure.zhazhky.SummaryTask4.web.validators.Validator;
import ua.nure.zhazhky.SummaryTask4.web.webUtils.ValidatorUtil;

import java.util.regex.Pattern;

public class PasswordValidator implements Validator<String> {

    private static final String REGEX_FOR_PASSWORD = "(\\pL|\\d){5,16}";

    private static final Pattern PATTERN_FOR_PASSWORD = Pattern.compile(REGEX_FOR_PASSWORD);

    private static PasswordValidator passwordValidator = new PasswordValidator();

    private PasswordValidator() {
    }

    public static PasswordValidator getInstance(){
        return passwordValidator;
    }

    @Override
    public void validate(String password) throws ValidateException {
        if (password == null || !ValidatorUtil.matchPattern(PATTERN_FOR_PASSWORD, password)) {
            throw new IncorrectPasswordException("Incorrect user password, " + System.lineSeparator() +
                    "user password must be longer than 4 symbols!");
        }
    }
}
