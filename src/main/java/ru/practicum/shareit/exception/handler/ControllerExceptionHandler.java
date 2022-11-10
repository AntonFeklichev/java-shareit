package ru.practicum.shareit.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.practicum.shareit.exception.*;

import javax.validation.ValidationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice(
        basePackages = {"ru.practicum.shareit.user",
                "ru.practicum.shareit"})
public class ControllerExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Map<String, String> validationError(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        StringBuilder builder = new StringBuilder();
        List<FieldError> errors = result.getFieldErrors();
        Map<String, String> responseMap = new HashMap<>();
        for (FieldError error : errors) {
            responseMap.put(error.getField(), error.getDefaultMessage());
        }
        return responseMap;
    }

    @ExceptionHandler({UserNotFoundException.class, ItemNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleDataNotFoundException(final DataNotFoundException e) {
        return new ErrorResponse(e.getMessage());
    }

    @ExceptionHandler({AutentificationFailedException.class})
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorResponse handleIllegalArgumentException(final AutentificationFailedException e) {
        return new ErrorResponse(e.getMessage());
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleValidationException(final EmailAlreadyExistsException e) {
        return new ErrorResponse(e.getMessage());
    }

    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleValidationException(final ValidationException e) {
        return new ErrorResponse(e.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleDefaultException(final Exception e) {
        return new ErrorResponse("Unexpected error!");
    }
}