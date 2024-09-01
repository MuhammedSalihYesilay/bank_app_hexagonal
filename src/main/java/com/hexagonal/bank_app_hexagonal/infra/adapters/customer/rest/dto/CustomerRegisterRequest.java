package com.hexagonal.bank_app_hexagonal.infra.adapters.customer.rest.dto;

import com.hexagonal.bank_app_hexagonal.domain.customer.usecase.CustomerRegister;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRegisterRequest {

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    private String phoneNumber;

    @Past
    private LocalDateTime dateOfBirth;

    public static CustomerRegister toCustomerRegister(CustomerRegisterRequest request) {
        return CustomerRegister.builder()
                .email(request.getEmail())
                .password(request.getPassword())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .phoneNumber(request.getPhoneNumber())
                .dateOfBirth(request.getDateOfBirth())
                .build();
    }
}




