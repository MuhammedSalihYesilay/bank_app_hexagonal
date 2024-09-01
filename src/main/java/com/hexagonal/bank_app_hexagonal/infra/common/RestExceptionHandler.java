package com.hexagonal.bank_app_hexagonal.infra.common;

import com.hexagonal.bank_app_hexagonal.domain.account.exception.AccountNotFoundException;
import com.hexagonal.bank_app_hexagonal.domain.customer.exception.CustomerNotFoundException;
import com.hexagonal.bank_app_hexagonal.domain.customer.exception.EmailAlreadyInUseException;
import com.hexagonal.bank_app_hexagonal.domain.transaction.exception.InsufficientBalanceException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class RestExceptionHandler {

    // FOR REQUEST VALIDATION
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected Map<String, String> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return errors;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(AccountNotFoundException.class)
    protected ErrorResponse handleAccountNotFoundException(AccountNotFoundException exception) {
        return new ErrorResponse(exception.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InsufficientBalanceException.class)
    protected ErrorResponse handleBalanceIsNotEnoughException(InsufficientBalanceException exception) {
        return new ErrorResponse(exception.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(EmailAlreadyInUseException.class)
    protected ErrorResponse handleUserEmailAlreadyInUseException(EmailAlreadyInUseException exception) {
        return new ErrorResponse(exception.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(CustomerNotFoundException.class)
    protected ErrorResponse handleCustomerNotFoundException(CustomerNotFoundException exception) {
        return new ErrorResponse(exception.getMessage());
    }
}
