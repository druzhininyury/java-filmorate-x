package ru.yandex.practicum.filmorate;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.filmorate.controller.FilmController;
import ru.yandex.practicum.filmorate.model.Film;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class FilmControllerTest {

    @Test
    public void filmValidationTest() {
        Film film;
        film = new Film( null, "Description", LocalDate.now(), 90);
        assertFalse(FilmController.isFilmValid(film));
        film = new Film( "", "Description", LocalDate.now(), 90);
        assertFalse(FilmController.isFilmValid(film));
        film = new Film( "Name", null, LocalDate.now(), 90);
        assertFalse(FilmController.isFilmValid(film));
        film = new Film("Name",
                "a".repeat(FilmController.FILM_DESCRIPTION_MAX_LENGTH + 1),
                LocalDate.now(),
                161);
        assertFalse(FilmController.isFilmValid(film));
        film = new Film( "Name", "Description", null, 90);
        assertFalse(FilmController.isFilmValid(film));
        film = new Film( "Name", "Description", LocalDate.of(1870, 1, 1), 90);
        assertFalse(FilmController.isFilmValid(film));
        film = new Film( "Name", "Description", LocalDate.of(1895, 12, 28), 90);
        assertTrue(FilmController.isFilmValid(film));
        film = new Film( "Name", "Description", LocalDate.now(), -10);
        assertFalse(FilmController.isFilmValid(film));
        film = new Film( "Name", "Description", LocalDate.now(), 0);
        assertFalse(FilmController.isFilmValid(film));
    }
}
