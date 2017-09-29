package ua.nure.zhazhky.SummaryTask4.web.validators.card;

import ua.nure.zhazhky.SummaryTask4.exceptions.validate.ValidateException;
import ua.nure.zhazhky.SummaryTask4.exceptions.validate.cardValidate.IncorrectCardPINException;
import ua.nure.zhazhky.SummaryTask4.web.validators.Validator;
import ua.nure.zhazhky.SummaryTask4.web.webUtils.ValidatorUtil;

import java.util.regex.Pattern;

public class CardPINValidator implements Validator<String> {

    private static final String REGEX_FOR_CARD_PIN = "(\\d){4}";

    private static final Pattern PATTERN_FOR_CARD_PIN = Pattern.compile(REGEX_FOR_CARD_PIN);

    private static  CardPINValidator cardPINValidator = new CardPINValidator();

    private CardPINValidator() {
    }

    public static CardPINValidator getInstance(){
        return cardPINValidator;
    }

    @Override
    public void validate(String account_id) throws ValidateException {
        String value = String.valueOf(account_id);
        if (value == null || !ValidatorUtil.matchPattern(PATTERN_FOR_CARD_PIN, value)) {
            throw new IncorrectCardPINException("Incorrect PIN-code, " + System.lineSeparator() +
                    "PIN-code must consist of 4 numbers!");
        }
    }
}
