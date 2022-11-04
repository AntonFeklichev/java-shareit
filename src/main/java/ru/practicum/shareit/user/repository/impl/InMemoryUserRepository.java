package ru.practicum.shareit.user.repository.impl;

import org.springframework.stereotype.Repository;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.user.repository.UserRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class InMemoryUserRepository implements UserRepository {

    private Map<Long, User> users = new HashMap<>();

    @Override
    public User addUser(final User user) {
        users.put(user.getId(), user);
        return user;
    }

    @Override
    public void removeUserById(final Long userId) {
        users.remove(userId);
    }

    @Override
    public User updateUser(final User user) {
        users.replace(user.getId(), user);
        return user;
    }

    @Override
    public User findById(final Long userId) {
        return users.get(userId);
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(users.values());
    }
}
