package ru.practicum.shareit.user.validator;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.practicum.shareit.user.dto.UserDto;

@Component("beforeCreateUserControllerValidator")
@Slf4j
public class UserPatchValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(UserDto.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserDto dto = (UserDto) target;
        if (!emailOfUserIsValid(dto)) {
            log.info("invalid email");
            errors.rejectValue("email", "email.invalid");
        }

        if (!nameOfUserIsValid(dto)) {
            log.info("invalid name");
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
