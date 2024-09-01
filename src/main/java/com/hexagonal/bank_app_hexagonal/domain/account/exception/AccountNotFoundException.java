package com.hexagonal.bank_app_hexagonal.domain.account.exception;

public class AccountNotFoundException extends RuntimeException{
    public AccountNotFoundException(String details) {
        super("Account not found. " + details);
    }
}
