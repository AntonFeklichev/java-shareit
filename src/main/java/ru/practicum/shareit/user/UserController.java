package ru.practicum.shareit.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.user.service.UserService;
import ru.practicum.shareit.user.validator.UserPatchValidator;

import javax.validation.Valid;
import java.util.List;

/**
 * TODO Sprint add-controllers.
 */
@RestController
@RequestMapping(path = "/users")
public class UserController {
    private final UserService userService;
    private final UserPatchValidator userPatchValidator;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setValidator(userPatchValidator);
    }

    @Autowired
    public UserController(UserService userService, UserPatchValidator userPatchValidator) {
        this.userService = userService;
        this.userPatchValidator = userPatchValidator;
    }

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
    public UserDto updateUser(@Validated(UserPatchValidator.class)
                              @RequestBody
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
