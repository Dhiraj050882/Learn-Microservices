package com.learnmicroservices.cards;

import com.learnmicroservices.cards.dto.CardsContactInfoDto;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing(auditorAwareRef = "cardsAuditAwareImpl")
@EnableConfigurationProperties(CardsContactInfoDto.class)
@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Cards microservice REST API Documentation",
				description = "Dummy Bank Cards microservice REST API Documentation",
				version = "v1",
				contact = @Contact(
						name = "Dhiraj Singh",
						email = "cards@dummybank.com",
						url = "https://www.dummybank.com"
				),
				license = @License(
						name = "Apache 2.0",
						url = "https://www.apache.com"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "Dummy Bank Cards microservice REST API Documentation",
				url = "https://www.eazybytes.com/swagger-ui.html"
		)
)
public class CardsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CardsApplication.class, args);
	}

}
