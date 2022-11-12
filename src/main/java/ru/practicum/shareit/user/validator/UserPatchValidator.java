package ru.practicum.shareit.user.validator;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.stereotype.Component;
import ru.practicum.shareit.user.entity.User;

import javax.validation.ValidationException;

@Component
public class UserPatchValidator {

    public void validate(User user) {
        if (!emailOfUserIsValid(user)) {
            throw new ValidationException("Invalid email");
        }

        if (!nameOfUserIsValid(user)) {
            throw new ValidationException("Invalid name");
        }
    }

    private boolean nameOfUserIsValid(User user) {
        String name = user.getName();
        return name == null || !name.isEmpty();
    }

    private boolean emailOfUserIsValid(User user) {
        String email = user.getEmail();
        return email == null || EmailValidator.getInstance().isValid(email);
    }
}
