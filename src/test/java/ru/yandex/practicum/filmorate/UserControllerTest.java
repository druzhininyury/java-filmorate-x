package ru.yandex.practicum.filmorate;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.filmorate.controller.UserController;
import ru.yandex.practicum.filmorate.model.User;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class UserControllerTest {

    @Test
    public void userValidationTest() {
        User user;
        user = new User( "mail", "login", "Name", LocalDate.of(2000, 1, 1));
        assertFalse(UserController.isUserValid(user));
        user = new User( null, "login", "Name", LocalDate.of(2000, 1, 1));
        assertFalse(UserController.isUserValid(user));
        user = new User( "", "login", "Name", LocalDate.of(2000, 1, 1));
        assertFalse(UserController.isUserValid(user));
        user = new User( "mail@yandex.ru", null, "Name", LocalDate.of(2000, 1, 1));
        assertFalse(UserController.isUserValid(user));
        user = new User( "mail@yandex.ru", "", "Name", LocalDate.of(2000, 1, 1));
        assertFalse(UserController.isUserValid(user));
        user = new User( "mail@yandex.ru", "log in", "Name", LocalDate.of(2000, 1, 1));
        assertFalse(UserController.isUserValid(user));
        user = new User( "mail@yandex.ru", "login", "Name", LocalDate.now().plusYears(1));
        assertFalse(UserController.isUserValid(user));
        user = new User( "mail@yandex.ru", "login", "Name", LocalDate.now());
        assertTrue(UserController.isUserValid(user));
    }
}
