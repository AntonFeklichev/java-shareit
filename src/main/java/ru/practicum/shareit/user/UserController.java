package ru.practicum.shareit.user;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.user.service.UserService;

import javax.validation.Valid;
import java.util.List;

/**
 * TODO Sprint add-controllers.
 */
@RestController
@RequestMapping(path = "/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;


    @PostMapping
    public UserDto addUser(@Valid @RequestBody
                           UserDto userDto) {
        return userService.addUser(userDto);
    }

    @DeleteMapping("/{userId}")
    public void removeUserById(@PathVariable Long userId) {
        userService.removeUserById(userId);
    }

    @PatchMapping("/{userId}")
    public UserDto updateUser(@RequestBody
                              UserDto userDto,
                              @PathVariable
                              Long userId) {
        userDto.setId(userId);
        return userService.updateUser(userDto);
    }

    @GetMapping("/{userId}")
    public UserDto findById(@PathVariable Long userId) {
        return userService.findById(userId);
    }

    @GetMapping
    public List<UserDto> findAll() {
        return userService.findAll();
    }
}
