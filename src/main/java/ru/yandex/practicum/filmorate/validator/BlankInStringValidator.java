package ru.yandex.practicum.filmorate.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class BlankInStringValidator implements ConstraintValidator<CheckBlankInSring, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        for (int i = 0; i < value.length(); i++) {
            if (Character.isWhitespace(value.charAt(i))) {
                return false;
            }
        }
        return true;
    }

}