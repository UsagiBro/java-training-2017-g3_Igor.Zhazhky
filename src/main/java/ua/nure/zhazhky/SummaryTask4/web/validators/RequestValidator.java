package ua.nure.zhazhky.SummaryTask4.web.validators;

import ua.nure.zhazhky.SummaryTask4.exceptions.validate.IncorrectRequestException;
import ua.nure.zhazhky.SummaryTask4.exceptions.validate.ValidateException;
import ua.nure.zhazhky.SummaryTask4.web.webUtils.ValidatorUtil;

import java.util.regex.Pattern;

public class RequestValidator implements Validator<String> {

    private static final String REGEX_FOR_REQUEST = "(\\d){8}";

    private static final Pattern PATTERN_FOR_REQUEST = Pattern.compile(REGEX_FOR_REQUEST);

    private static RequestValidator requestValidator = new RequestValidator();

    private RequestValidator() {
    }

    public static RequestValidator getInstance(){
        return requestValidator;
    }

    @Override
    public void validate(String request) throws ValidateException {
        String value = String.valueOf(request);
        if (value == null || !ValidatorUtil.matchPattern(PATTERN_FOR_REQUEST, value)) {
            throw new IncorrectRequestException("Bad request!");
        }
    }
}
