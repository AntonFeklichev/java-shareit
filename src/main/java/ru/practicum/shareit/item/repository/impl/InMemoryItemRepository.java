package ru.practicum.shareit.item.repository.impl;

import org.springframework.stereotype.Repository;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.item.repository.ItemRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class InMemoryItemRepository implements ItemRepository {
    private Map<Long, Item> items = new HashMap<>();

    @Override
    public Item addItem(final Item item) {
        items.put(item.getId(), item);
        return item;
    }

    @Override
    public Item updateItem(Item item) {
        items.replace(item.getId(), item);
        return item;
    }

    @Override
    public Item findById(final Long itemId) {
        return items.get(itemId);
    }

    @Override
    public List<Item> findByOwnerId(final Long ownerId) {
        return items.values()
                .stream()
                .filter(item -> item.getUserId().equals(ownerId))
                .collect(Collectors.toList());
    }

    @Override
    public List<Item> findBySearch(String text) {
        return items.values()
                .stream()
                .filter(Item::isAvailable)
                .filter(item -> item.getName().toLowerCase().contains(text.toLowerCase()) ||
                        item.getDescription().toLowerCase().contains(text.toLowerCase()))
                .collect(Collectors.toList());
    }
}
