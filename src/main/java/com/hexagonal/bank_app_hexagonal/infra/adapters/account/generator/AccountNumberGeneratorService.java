package com.hexagonal.bank_app_hexagonal.infra.adapters.account.generator;

import com.hexagonal.bank_app_hexagonal.domain.account.port.AccountNumberGeneratorPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


@Service
@RequiredArgsConstructor
public class AccountNumberGeneratorService implements AccountNumberGeneratorPort {

    private final SecureRandom secureRandom = new SecureRandom();

    @Override
    public String generateAccountNumber() {
        return IntStream.range(0, 16)
                .mapToObj(i -> String.valueOf(secureRandom.nextInt(10)))
                .collect(Collectors.joining());
    }
}
