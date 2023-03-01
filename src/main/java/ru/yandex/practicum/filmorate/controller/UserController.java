package ru.yandex.practicum.filmorate.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.User;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.*;

@RestController
public class UserController {

    private int nextUserId = 1;
    private final Map<Integer, User> users = new HashMap<>();
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    private int getNewUserId() {
        return nextUserId++;
    }

    @GetMapping("/users")
    public Collection<User> getAll() {
        return users.values();
    }

    @PostMapping("/users")
    public User add(@Valid @RequestBody User user) throws ValidationException {
        if (isUserValid(user)) {
            if (user.getId() == 0) {
                user.setId(getNewUserId());
                if (user.getName() == null || user.getName().isEmpty()) {
                    user.setName(user.getLogin());
                }
                users.put(user.getId(), user);
                log.info("User information added.");
                return user;
            } else {
                log.warn("Received user id is not null.");
                throw new ValidationException("Received user id is not null.");
            }
        } else {
            log.warn("Received incorrect user information.");
            throw new ValidationException("Received incorrect user information.");
        }
    }

    @PutMapping("/users")
    public User update(@Valid @RequestBody User user) throws ValidationException {
        if (isUserValid(user)) {
            if (users.containsKey(user.getId())) {
                if (user.getName() == null || user.getName().isEmpty()) {
                    user.setName(user.getLogin());
                }
                users.put(user.getId(), user);
                log.info("User information updated.");
                return user;
            } else {
                log.warn("Received user id hasn't found.");
                throw new ValidationException("Received user id hasn't found.");
            }
        } else {
            log.warn("Received incorrect user information.");
            throw new ValidationException("Users parameters are incorrect.");
        }
    }

    public static boolean isUserValid(User user) {
        return user.getBirthday().isBefore(LocalDate.now()) || user.getBirthday().isEqual(LocalDate.now());
    }
}
