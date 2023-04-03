package ru.yandex.practicum.filmorate.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class DateFilmValidator implements ConstraintValidator<PastForFilm, LocalDate> {

    @Override
    public boolean isValid(LocalDate filmDate, ConstraintValidatorContext constraintValidatorContext) {
        LocalDate minDate = LocalDate.of(1895, 12, 28);
        return !minDate.isAfter(filmDate);
    }
}
