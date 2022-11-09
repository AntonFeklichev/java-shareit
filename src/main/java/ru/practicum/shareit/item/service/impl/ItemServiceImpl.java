package ru.practicum.shareit.item.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.entity.Item;
import ru.practicum.shareit.item.exception.ItemNotFoundException;
import ru.practicum.shareit.item.mapper.ItemMapper;
import ru.practicum.shareit.item.repository.ItemRepository;
import ru.practicum.shareit.item.service.ItemService;
import ru.practicum.shareit.user.exception.UserNotFoundException;
import ru.practicum.shareit.user.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemServiceImpl implements ItemService {
    private final ItemMapper itemMapper;
    private final ItemRepository itemRepository;
    private final UserRepository userRepository;

    @Autowired
    public ItemServiceImpl(ItemMapper itemMapper, ItemRepository itemRepository, UserRepository userRepository) {
        this.itemMapper = itemMapper;
        this.itemRepository = itemRepository;
        this.userRepository = userRepository;
    }

    @Override
    public ItemDto addItem(ItemDto itemDto, Long sharerId) throws UserNotFoundException {
        userRepository.findById(sharerId).orElseThrow(() -> new UserNotFoundException("Sharer not found"));
        Item item = itemMapper.toItem(itemDto, sharerId);
        item = itemRepository.addItem(item);
        return itemMapper.toDto(item);
    }

    @Override
    public ItemDto findById(Long itemId) {
        return itemMapper.toDto(itemRepository.findById(itemId).orElseThrow(
                () -> new ItemNotFoundException("Item not found")));
    }

    @Override
    public List<ItemDto> findAll() {
        return itemRepository.findAll()
                .stream()
                .map(itemMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ItemDto> findAllByOwnerId(Long ownerId) {
        return itemRepository.findByOwnerId(ownerId).stream()
                .map(itemMapper::toDto)
                .collect(Collectors.toList());
    }
}
