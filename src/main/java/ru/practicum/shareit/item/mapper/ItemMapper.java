package ru.practicum.shareit.item.mapper;

import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.model.Item;

public class ItemMapper {

    public Item toItem(ItemDto dto, Long userId) {
        return Item.builder()
                .id(dto.getId())
                .userId(userId)
                .name(dto.getName())
                .description(dto.getDescription())
                .available(dto.isAvailable())
                .build();
    }

    public ItemDto toDto(Item item) {
        return ItemDto.builder()
                .id(item.getId())
                .name(item.getName())
                .description(item.getDescription())
                .available(item.isAvailable())
                .build();
    }
}
