package ru.practicum.shareit.exception;

public class ItemNotFoundException extends DataNotFoundException {
    public ItemNotFoundException(String message) {
        super(message);
    }
}
