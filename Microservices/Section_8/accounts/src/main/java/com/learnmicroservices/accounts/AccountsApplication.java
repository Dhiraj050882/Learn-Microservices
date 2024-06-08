package com.learnmicroservices.accounts;

import com.learnmicroservices.accounts.dto.AccountsContactInfoDto;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@SpringBootApplication
@EnableConfigurationProperties(value = AccountsContactInfoDto.class)
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@EnableFeignClients
@OpenAPIDefinition(info =
							@Info(
									title = "Accounts Webservice",
									description = "REST API for performing Customer and Accounts operations",
									version = "v0.0_Base",
									contact = @Contact(
											name = "Account Support Team",
											email = "accounts@dummybank.com",
											url="https://www.dummybank.com"
									),
									license = @License(
											name = "Apache 2.0",
											url = "https://www.apache.com"
									)

							),
		externalDocs = @ExternalDocumentation(
				description = "Indian bank accounts Microservice REST API Documentation",
				url = "https://www.dummybankurl.com"
		)

)
public class AccountsApplication {

	public static void main(String[] args) {

		SpringApplication.run(AccountsApplication.class, args);
	}

}
