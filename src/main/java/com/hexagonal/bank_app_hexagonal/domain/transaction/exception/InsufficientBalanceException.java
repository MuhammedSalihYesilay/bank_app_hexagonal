package com.hexagonal.bank_app_hexagonal.domain.transaction.exception;

public class InsufficientBalanceException extends RuntimeException {
    public InsufficientBalanceException() {
        super("Insufficient balance.");
    }
}