package ru.practicum.shareit.item.service;

import org.springframework.stereotype.Service;
import ru.practicum.shareit.item.dto.ItemDto;

public interface ItemService {

    ItemDto addItem(ItemDto itemDto, Long sharerId);
}
