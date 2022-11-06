package ru.practicum.shareit.user.exception;

import javax.validation.ValidationException;

public class EmailAlreadyExistsException extends ValidationException {
    public EmailAlreadyExistsException(String message) {
        super(message);
    }
}
