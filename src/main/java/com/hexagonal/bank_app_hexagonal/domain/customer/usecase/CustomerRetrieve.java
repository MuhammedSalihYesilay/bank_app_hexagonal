package com.hexagonal.bank_app_hexagonal.domain.customer.usecase;


import com.hexagonal.bank_app_hexagonal.domain.common.model.UseCase;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CustomerRetrieve implements UseCase {

    private String email;
}
