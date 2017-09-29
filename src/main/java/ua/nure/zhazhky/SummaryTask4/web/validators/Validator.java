package ua.nure.zhazhky.SummaryTask4.web.validators;

import ua.nure.zhazhky.SummaryTask4.exceptions.validate.ValidateException;

public interface Validator<T> {

    void validate(T field) throws ValidateException;
}
