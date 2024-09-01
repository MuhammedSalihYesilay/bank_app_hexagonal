package com.hexagonal.bank_app_hexagonal.domain.customer.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class Customer {

    private String id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private LocalDateTime dateOfBirth;
}
