package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exception.NotFoundException;
import ru.yandex.practicum.filmorate.model.Film;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/films")
public class FilmController {

    private final HashMap<Integer, Film> filmHashMap = new HashMap<>();
    private int generateUniqueId = 1;


    @GetMapping()
    public List<Film> getAllFilm() {
        return new ArrayList<>(filmHashMap.values());
    }

    @PostMapping()
    public Film createFilm(@Valid @RequestBody Film newFilm) {
        newFilm.setId(generateUniqueId);
        filmHashMap.put(newFilm.getId(), newFilm);
        incrementUniqueId();
        log.info("Фильм создан!");
        return newFilm;
    }

    @PutMapping()
    public Film updateFilm(@Valid @RequestBody Film film) {
        if (filmHashMap.containsKey(film.getId())) {
            filmHashMap.put(film.getId(), film);
            log.info("Фильм обновлен!");
            return film;
        } else {
            NotFoundException exception = new NotFoundException(String.valueOf(film.getId()));
            log.error(exception.getMessage());
            throw exception;
        }
    }

    private void incrementUniqueId() {
        generateUniqueId++;
    }

}
