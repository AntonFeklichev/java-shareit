package ru.practicum.shareit.exception;

public class AutentificationFailedException extends RuntimeException {
    public AutentificationFailedException(String message) {
        super(message);
    }
}
