package ru.practicum.shareit.item.repository;

import ru.practicum.shareit.item.entity.Item;

import java.util.List;

public interface ItemRepository {
    Item addItem(Item item);

    Item updateItem(Item item);

    Item findById(Long itemId);

    List<Item> findByOwnerId(Long ownerId);

    List<Item> findBySearch(String text);
}
