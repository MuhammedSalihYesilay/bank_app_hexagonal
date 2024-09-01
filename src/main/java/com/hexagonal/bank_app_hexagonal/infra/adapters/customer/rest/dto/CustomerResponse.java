package com.hexagonal.bank_app_hexagonal.infra.adapters.customer.rest.dto;


import com.hexagonal.bank_app_hexagonal.domain.customer.model.Customer;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class CustomerResponse {
    private String id;
    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private LocalDateTime dateOfBirth;

    public static CustomerResponse from(Customer customer) {
        return CustomerResponse.builder()
                .id(customer.getId())
                .email(customer.getEmail())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .phoneNumber(customer.getPhoneNumber())
                .dateOfBirth(customer.getDateOfBirth())
                .build();
    }
}
