package ua.nure.zhazhky.SummaryTask4.web.validators.user;

import ua.nure.zhazhky.SummaryTask4.exceptions.validate.ValidateException;
import ua.nure.zhazhky.SummaryTask4.exceptions.validate.userValidate.IncorrectLoginException;
import ua.nure.zhazhky.SummaryTask4.web.validators.Validator;
import ua.nure.zhazhky.SummaryTask4.web.webUtils.ValidatorUtil;

import java.util.regex.Pattern;

public class LoginValidator implements Validator<String> {

    private static final String REGEX_FOR_LOGIN = "\\pL{3,16}";

    private static final Pattern PATTERN_FOR_LOGIN = Pattern.compile(REGEX_FOR_LOGIN);

    private static LoginValidator loginValidator = new LoginValidator();

    private LoginValidator() {
    }

    public static LoginValidator getInstance(){
        return loginValidator;
    }

    @Override
    public void validate(String login) throws ValidateException {
        if (login == null || !ValidatorUtil.matchPattern(PATTERN_FOR_LOGIN, login)) {
            throw new IncorrectLoginException("Incorrect user login, " + System.lineSeparator() +
                    "user login must contain only letters and be longer than 3 symbols!");
        }
    }
}
