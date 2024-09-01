package com.hexagonal.bank_app_hexagonal.infra.adapters.customer.rest.dto;

import com.hexagonal.bank_app_hexagonal.infra.common.Response;

public class RegisterResponse extends Response {
    public RegisterResponse() {
        super("User successfully registered.");
    }
}
