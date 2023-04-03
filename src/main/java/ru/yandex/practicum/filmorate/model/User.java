package ru.yandex.practicum.filmorate.model;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import ru.yandex.practicum.filmorate.validator.CheckBlankInSring;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.time.LocalDate;


@Data
@Builder
public class User {

    private int id;

    @NonNull
    @Email
    @NotBlank
    private final String email;

    @NonNull
    @NotBlank
    @CheckBlankInSring
    private final String login;

    private String name;

    @Past
    private final LocalDate birthday;
}
