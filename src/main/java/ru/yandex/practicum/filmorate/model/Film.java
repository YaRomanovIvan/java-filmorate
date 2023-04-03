package ru.yandex.practicum.filmorate.model;

import lombok.Data;
import lombok.NonNull;
import ru.yandex.practicum.filmorate.validator.PastForFilm;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
public class Film {

    private int id;

    @NonNull
    @NotBlank
    private final String name;

    @Size(max = 200)
    private final String description;

    @PastForFilm
    private final LocalDate releaseDate;

    @Positive
    private final int duration;

}
