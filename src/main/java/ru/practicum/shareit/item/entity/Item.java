package ru.practicum.shareit.item.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

/**
 * TODO Sprint add-controllers.
 */
@Data
@Builder
public class Item {
    private Long id;
    private Long ownerId;
    private String name;
    private String description;
    @Getter(AccessLevel.NONE)
    private Boolean available;
    private Long requestId;

    public Boolean isAvailable() {
        return available;
    }
}
