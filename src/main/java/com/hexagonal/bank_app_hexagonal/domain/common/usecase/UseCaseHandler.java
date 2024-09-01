package com.hexagonal.bank_app_hexagonal.domain.common.usecase;

import com.hexagonal.bank_app_hexagonal.domain.common.model.UseCase;

public interface UseCaseHandler<E, T extends UseCase> {
    E handle(T useCase);
}
