package ru.practicum.shareit.item.service;

import ru.practicum.shareit.item.dto.ItemDto;

import java.util.List;

public interface ItemService {

    ItemDto addItem(ItemDto itemDto, Long sharerId);

    ItemDto findById(Long itemId);

    List<ItemDto> findAll();

    List<ItemDto> findAllByOwnerId(Long ownerId);
}
