package ru.practicum.shareit.item.valdator;

import org.springframework.stereotype.Component;
import ru.practicum.shareit.item.dto.ItemDto;

import javax.validation.ValidationException;

@Component
public class ItemPatchValidator {

    public void validate(ItemDto dto) {
        if (!nameOfItemIsValid(dto)) {
            throw new ValidationException("invalid name");
        }

        if (!descriptionOfItemIsValid(dto)) {
            throw new ValidationException("invalid description");
        }
    }

    private boolean nameOfItemIsValid(ItemDto dto) {
        String name = dto.getName();
        return name == null || !name.isBlank();
    }

    private boolean descriptionOfItemIsValid(ItemDto dto) {
        String description = dto.getDescription();
        return description == null || !description.isBlank();
    }

}
