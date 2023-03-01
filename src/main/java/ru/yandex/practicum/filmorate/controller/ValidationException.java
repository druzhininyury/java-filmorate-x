package ru.yandex.practicum.filmorate.controller;

public class ValidationException extends Exception {

    public ValidationException() {

    }

    public ValidationException(String message) {
        super(message);
    }
}
