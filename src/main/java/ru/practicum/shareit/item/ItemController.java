package ru.practicum.shareit.item;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.service.ItemService;

import javax.validation.Valid;
import java.util.List;

/**
 * TODO Sprint add-controllers.
 */
@RestController
@RequestMapping("/items")
@Slf4j
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

    @PostMapping
    public ItemDto addItem(@RequestHeader("x-sharer-user-id")
                           Long sharerId,
                           @RequestBody
                           @Valid
                           ItemDto itemDto) {
        return itemService.addItem(itemDto, sharerId);
    }

    @GetMapping("/{itemId}")
    public ItemDto findById(@PathVariable Long itemId) {
        return itemService.findById(itemId);
    }

    @GetMapping
    public List<ItemDto> findAllByOwnerId(@RequestHeader(value = "x-sharer-user-id", required = false)
                                          Long ownerId) {
        if (ownerId == null) {
            return itemService.findAll();
        } else {
            return itemService.findAllByOwnerId(ownerId);
        }
    }

    @PatchMapping("/{itemId}")
    public ItemDto updateItem(@RequestBody
                              ItemDto itemDto,
                              @RequestHeader(value = "x-sharer-user-id")
                              Long ownerId,
                              @PathVariable Long itemId) {
        itemDto.setId(itemId);
        return itemService.updateItem(itemDto, ownerId);
    }

    @GetMapping("/search")
    public List<ItemDto> findBySearch(@RequestParam String text) {
        return itemService.findBySearch(text);
    }
}
