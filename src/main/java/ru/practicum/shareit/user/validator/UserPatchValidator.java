package ru.practicum.shareit.user.validator;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.practicum.shareit.user.dto.UserDto;

@Component("beforeCreateUserControllerValidator")
public class UserPatchValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(UserDto.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserDto dto = (UserDto) target;
        if (!emailOfUserIsValid(dto)) {
            errors.rejectValue("email", "email.invalid");
        }

        if (!nameOfUserIsValid(dto)) {
            errors.rejectValue("name", "name.invalid");
        }
    }

    private boolean nameOfUserIsValid(UserDto dto) {
        String name = dto.getName();
        return name == null || !name.isEmpty();
    }

    private boolean emailOfUserIsValid(UserDto dto) {
        String email = dto.getEmail();
        return email == null || EmailValidator.getInstance().isValid(email);
    }
}
