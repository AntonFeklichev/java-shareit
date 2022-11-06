package ru.practicum.shareit.user.repository;

import ru.practicum.shareit.user.model.User;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;
import java.util.Optional;

public interface UserRepository {
    User addUser(@Valid User user);

    void removeUserById(@Positive Long userId);

    User updateUser(@Valid User user);

    Optional<User> findById(@Positive Long userId);

    List<User> findAll();
}
