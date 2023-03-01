package ru.yandex.practicum.filmorate.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import java.time.LocalDate;
import java.util.Objects;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class User {

    private int id;
    @Email(message = "Incorrect e-mail.")
    private String email;
    @NotBlank(message = "Login mustn't be blank.")
    @Pattern(regexp = "\\w+", message = "Login must contains only word characters, digits or underscore.")
    private String login;
    private String name;
    @NotNull
    private LocalDate birthday;

}
