package ru.practicum.shareit.item.mapper;

import org.springframework.stereotype.Component;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.entity.Item;

@Component
public class ItemMapper {

    public Item toItem(ItemDto dto, Long ownerId) {
        return Item.builder()
                .id(dto.getId())
                .ownerId(ownerId)
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
