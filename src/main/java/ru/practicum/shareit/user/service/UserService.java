package ru.practicum.shareit.user.service;

import ru.practicum.shareit.user.dto.UserDto;

import java.util.List;

public interface UserService {

    UserDto addUser(UserDto userDto);

    void removeUserById(Long userId);

    UserDto findById(Long userId);

    UserDto updateUser(UserDto userDto);

    List<UserDto> findAll();
}
