package ru.practicum.shareit.user.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.exception.EmailAlreadyExistsException;
import ru.practicum.shareit.exception.UserNotFoundException;
import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.user.entity.User;
import ru.practicum.shareit.user.mapper.UserMapper;
import ru.practicum.shareit.user.repository.UserRepository;
import ru.practicum.shareit.user.service.UserService;
import ru.practicum.shareit.user.validator.UserPatchValidator;

import javax.validation.ValidationException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final UserPatchValidator userPatchValidator;

    @Override
    public UserDto addUser(UserDto userDto) {
        User user = userMapper.toUser(userDto);
        checkThatEmailOfUserIsUnique(user);
        user = userRepository.addUser(user);
        return userMapper.toDto(user);
    }

    private void checkThatEmailOfUserIsUnique(final User user) throws EmailAlreadyExistsException {
        if (user.getEmail() == null) throw new ValidationException("Email cannot be null");
        if (userRepository.findAll().stream()
                .anyMatch(existingUser -> existingUser.getEmail().equals(user.getEmail()))) {
            throw new EmailAlreadyExistsException("User with the same email already exists");
        }
    }

    @Override
    public void removeUserById(Long userId) {
        userRepository.removeUserById(userId);
    }

    @Override
    public UserDto findById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("Cannot find user"));
        return userMapper.toDto(user);
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        User updatedUser = userMapper.toUser(userDto);
        User oldUser = userRepository.findById(userDto.getId())
                .orElseThrow(() -> new UserNotFoundException("Cannot find user for updating"));
        userPatchValidator.validate(updatedUser);
        mapUserForUpdating(updatedUser, oldUser);
        userRepository.updateUser(updatedUser);
        return userMapper.toDto(updatedUser);
    }

    private void mapUserForUpdating(User updatedUser, User oldUser) {
        if (updatedUser.getName() == null) {
            updatedUser.setName(oldUser.getName());
        }
        if (updatedUser.getEmail() == null) {
            updatedUser.setEmail(oldUser.getEmail());
        } else {
            checkThatEmailOfUserIsUnique(updatedUser);
        }
    }

    @Override
    public List<UserDto> findAll() {
        List<User> allUsers = userRepository.findAll();
        return allUsers.stream().map(userMapper::toDto).collect(Collectors.toList());
    }
}
