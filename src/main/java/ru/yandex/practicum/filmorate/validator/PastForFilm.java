package ru.yandex.practicum.filmorate.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD}) // к чему будем применять (к полю)
@Retention(RUNTIME) // на каком этапе
@Constraint(validatedBy = DateFilmValidator.class) // реализация валидации
@Documented
public @interface PastForFilm {
    String message() default "FieldValidator.invalid";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
