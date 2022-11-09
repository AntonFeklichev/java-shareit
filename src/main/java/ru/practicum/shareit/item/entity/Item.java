package ru.practicum.shareit.item.entity;

import lombok.Builder;
import lombok.Data;

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
    private boolean available;
    private Long requestId;
}
