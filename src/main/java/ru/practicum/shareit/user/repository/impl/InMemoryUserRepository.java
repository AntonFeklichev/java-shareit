package ru.practicum.shareit.user.repository.impl;

import org.springframework.stereotype.Repository;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.user.repository.UserRepository;

import java.util.*;

@Repository
public class InMemoryUserRepository implements UserRepository {

    private Map<Long, User> users = new HashMap<>();

    private Long nextId = 1L;

    private Long getNextId() {
        return nextId++;
    }

    @Override
    public User addUser(User user) {
        user.setId(getNextId());
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
    public Optional<User> findById(final Long userId) {
        return Optional.ofNullable(users.get(userId));
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(users.values());
    }
}
