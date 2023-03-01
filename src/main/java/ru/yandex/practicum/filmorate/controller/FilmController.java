package ru.yandex.practicum.filmorate.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.Film;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.*;

@RestController
public class FilmController {

    public static final int FILM_DESCRIPTION_MAX_LENGTH = 200;
    private int nextFilmId = 1;
    private final Map<Integer,Film> films = new HashMap<>();
    private static final Logger log = LoggerFactory.getLogger(FilmController.class);

    private int getNewFilmId() {
        return nextFilmId++;
    }

    @GetMapping("/films")
    public Collection<Film> getAll() {
        return films.values();
    }

    @PostMapping("/films")
    public Film add(@Valid @RequestBody Film film) throws ValidationException {
        if (isFilmValid(film)) {
            if (film.getId() == 0) {
                film.setId(getNewFilmId());
                films.put(film.getId(), film);
                log.info("Film information added.");
                return film;
            } else {
                throw new ValidationException("Film id parameter is not 0.");
            }
        } else {
            log.warn("Received incorrect film data.");
            throw new ValidationException("Film parameters are incorrect.");
        }
    }

    @PutMapping("/films")
    public Film update(@Valid @RequestBody Film film) throws ValidationException {
        if (isFilmValid(film)) {
            if (films.containsKey(film.getId())) {
                films.put(film.getId(), film);
                log.info("Film information updated.");
                return film;
            } else {
                throw new ValidationException("Film id parameter hasn't found");
            }
        } else {
            log.warn("Received incorrect film data.");
            throw new ValidationException("Film parameters are incorrect.");
        }
    }

    public static boolean isFilmValid(Film film) {
        return film.getReleaseDate().isAfter(LocalDate.of(1895, 12, 28))
                    || film.getReleaseDate().isEqual(LocalDate.of(1895, 12, 28));
    }
}
