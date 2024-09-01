package com.hexagonal.bank_app_hexagonal.domain.customer.exception;

public class EmailAlreadyInUseException extends RuntimeException {

    public EmailAlreadyInUseException(String email) {
        super("Email " + email + " already exists!");
    }
}
