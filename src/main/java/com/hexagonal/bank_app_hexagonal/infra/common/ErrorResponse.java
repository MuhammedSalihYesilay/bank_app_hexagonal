package com.hexagonal.bank_app_hexagonal.infra.common;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResponse {

    private String message;
}
