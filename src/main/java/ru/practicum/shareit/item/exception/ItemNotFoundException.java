package ru.practicum.shareit.item.exception;

import ru.practicum.shareit.user.exception.DataNotFoundException;

public class ItemNotFoundException extends DataNotFoundException {
    public ItemNotFoundException(String message) {
        super(message);
    }
}
