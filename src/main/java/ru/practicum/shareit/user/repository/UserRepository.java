package ru.practicum.shareit.user.repository;

import ru.practicum.shareit.user.model.User;

import java.util.List;

public interface UserRepository {
    User addUser(User user);

    void removeUserById(Long userId);

    User updateUser(User user);

    User findById(Long userId);

    List<User> findAll();
}
