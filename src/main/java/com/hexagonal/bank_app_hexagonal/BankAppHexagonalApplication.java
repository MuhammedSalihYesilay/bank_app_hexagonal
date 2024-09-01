package com.hexagonal.bank_app_hexagonal;

import com.hexagonal.bank_app_hexagonal.domain.common.DomainComponent;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;


@SpringBootApplication
@ComponentScan(
		includeFilters = {
				@ComponentScan.Filter(type = FilterType.ANNOTATION, value = {DomainComponent.class})
		}
)
public class BankAppHexagonalApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankAppHexagonalApplication.class, args);
	}

}
