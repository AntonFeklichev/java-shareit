package ru.practicum.shareit.item.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.exception.AutentificationFailedException;
import ru.practicum.shareit.exception.ItemNotFoundException;
import ru.practicum.shareit.exception.UserNotFoundException;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.entity.Item;
import ru.practicum.shareit.item.mapper.ItemMapper;
import ru.practicum.shareit.item.repository.ItemRepository;
import ru.practicum.shareit.item.service.ItemService;
import ru.practicum.shareit.item.valdator.ItemPatchValidator;
import ru.practicum.shareit.user.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ItemServiceImpl implements ItemService {
    private final ItemMapper itemMapper;
    private final ItemRepository itemRepository;
    private final UserRepository userRepository;

    private final ItemPatchValidator itemPatchValidator;

    @Autowired
    public ItemServiceImpl(ItemMapper itemMapper, ItemRepository itemRepository, UserRepository userRepository, ItemPatchValidator itemPatchValidator) {
        this.itemMapper = itemMapper;
        this.itemRepository = itemRepository;
        this.userRepository = userRepository;
        this.itemPatchValidator = itemPatchValidator;
    }

    @Override

    public ItemDto addItem(ItemDto itemDto, Long sharerId) {
        checkThatSharerExists(sharerId);
        Item item = itemMapper.toItem(itemDto, sharerId);
        item = itemRepository.addItem(item);
        return itemMapper.toDto(item);
    }


    private void checkThatSharerExists(Long sharerId) throws UserNotFoundException {
        userRepository.findById(sharerId)
                .orElseThrow(() -> new UserNotFoundException("Sharer not found"));
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


    @Override
    public ItemDto updateItem(ItemDto itemDto, Long maybeOwnerId) throws ItemNotFoundException {
        checkThatSharerExists(maybeOwnerId);
        Item updatedItem = itemMapper.toItem(itemDto, maybeOwnerId);
        Item oldItem = itemRepository.findById(updatedItem.getId())
                .orElseThrow(() -> new ItemNotFoundException("Item for updating not found"));
        authenticateOwner(maybeOwnerId, oldItem);
        itemPatchValidator.validate(itemDto);
        updatedItem = mapItemForUpdating(oldItem, updatedItem);
        itemRepository.updateItem(updatedItem);
        return itemMapper.toDto(updatedItem);
    }

    private void authenticateOwner(Long maybeOwnerId, Item oldItem) throws AutentificationFailedException {
        Long realOwnerId = oldItem.getOwnerId();
        if (!realOwnerId.equals(maybeOwnerId)) {
            throw new AutentificationFailedException("You cannot update items of other users");
        }
    }

    @Override
    public List<ItemDto> findBySearch(String text) {
        if (text.isBlank()) return new ArrayList<>();
        return itemRepository.findBySearch(text).stream()
                .map(itemMapper::toDto)
                .collect(Collectors.toList());
    }

    private Item mapItemForUpdating(Item oldItem, Item updatedItem) {
        if (updatedItem.getName() == null) {
            updatedItem.setName(oldItem.getName());
        }
        if (updatedItem.getDescription() == null) {
            updatedItem.setDescription(oldItem.getDescription());
        }
        if (updatedItem.isAvailable() == null) {
            updatedItem.setAvailable(oldItem.isAvailable());
        }
        return updatedItem;
    }
}
