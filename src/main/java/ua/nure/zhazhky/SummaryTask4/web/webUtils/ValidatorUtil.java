package ua.nure.zhazhky.SummaryTask4.web.webUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class ValidatorUtil {

    private ValidatorUtil() {}

    public static boolean matchPattern(Pattern pattern, String text) {
        Matcher matcher = pattern.matcher(text);
        return matcher.matches();
    }
}
