package com.hexagonal.bank_app_hexagonal.domain.customer.exception;

public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException(String email) {
        super("Customer not found for email : " + email);
    }
}
