package com.hexagonal.bank_app_hexagonal.domain.adapters;

import com.hexagonal.bank_app_hexagonal.domain.customer.model.Customer;
import com.hexagonal.bank_app_hexagonal.domain.customer.port.CustomerPort;
import com.hexagonal.bank_app_hexagonal.domain.customer.usecase.CustomerRegister;

import java.time.LocalDateTime;

public class CustomerFakeDataAdapter implements CustomerPort {

    @Override
    public Customer create(CustomerRegister customerRegister) {
        return Customer.builder()
                .id("id")
                .email(customerRegister.getEmail())
                .firstName(customerRegister.getFirstName())
                .lastName(customerRegister.getLastName())
                .phoneNumber(customerRegister.getPhoneNumber())
                .dateOfBirth(customerRegister.getDateOfBirth())
                .build();
    }

    @Override
    public boolean isEmailAlreadyInUse(String email) {
        return false;
    }

    @Override
    public Customer retrieve(String email) {
        return Customer.builder()
                .id("id")
                .email("test email")
                .firstName("test first name")
                .lastName("test last name")
                .phoneNumber("0551 678 76 90")
                .dateOfBirth(LocalDateTime.of(2021, 1, 1, 20, 0, 0))
                .build();
    }
}
