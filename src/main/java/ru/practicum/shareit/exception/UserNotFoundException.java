package ru.practicum.shareit.exception;

public class UserNotFoundException extends DataNotFoundException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
