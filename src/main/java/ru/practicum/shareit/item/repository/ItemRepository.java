package ru.practicum.shareit.item.repository;

import ru.practicum.shareit.item.entity.Item;

import java.util.List;
import java.util.Optional;

public interface ItemRepository {
    Item addItem(Item item);

    Item updateItem(Item item);

    Optional<Item> findById(Long itemId);

    List<Item> findByOwnerId(Long ownerId);

    List<Item> findBySearch(String text);

    List<Item> findAll();
}
