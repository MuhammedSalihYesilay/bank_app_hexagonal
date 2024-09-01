package com.hexagonal.bank_app_hexagonal.domain.customer.usecase;

import com.hexagonal.bank_app_hexagonal.domain.common.model.UseCase;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class CustomerRegister implements UseCase{

    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private LocalDateTime dateOfBirth;
}
