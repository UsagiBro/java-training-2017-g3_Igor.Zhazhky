package ua.nure.zhazhky.SummaryTask4.web.validators.user;


import ua.nure.zhazhky.SummaryTask4.exceptions.validate.ValidateException;
import ua.nure.zhazhky.SummaryTask4.exceptions.validate.userValidate.IncorrectFullNameException;
import ua.nure.zhazhky.SummaryTask4.web.validators.Validator;
import ua.nure.zhazhky.SummaryTask4.web.webUtils.ValidatorUtil;

import java.util.regex.Pattern;

public class FullNameValidator implements Validator<String> {

    private static final String REGEX_FOR_FULL_NAME = "(\\pL|\\s){4,32}";

    private static final Pattern PATTERN_FOR_FULL_NAME = Pattern.compile(REGEX_FOR_FULL_NAME);

    private static FullNameValidator fullNameValidator = new FullNameValidator();

    private FullNameValidator() {
    }

    public static FullNameValidator getInstance(){
        return fullNameValidator;
    }

    @Override
    public void validate(String fullName) throws ValidateException {
        if (fullName == null || !ValidatorUtil.matchPattern(PATTERN_FOR_FULL_NAME, fullName)) {
            throw new IncorrectFullNameException("Incorrect full name, " + System.lineSeparator() +
                    "full name consist only letters and spaces");
        }
    }
}
