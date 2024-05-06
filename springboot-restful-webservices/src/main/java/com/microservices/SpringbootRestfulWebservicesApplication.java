package com.microservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@SpringBootApplication
// http://localhost:8080/swagger-ui/index.html
@OpenAPIDefinition(info = @Info(
		title = "Spring boot rest api",
		description = "Spring boot rest api documentation",
		version = "1.0.0",
		contact = @Contact(
				name = "Gaurav",
				email = "grvsgh7@gmail.com",
				url = "urlForSiteIfAny"
				),
		license = @License(
				name = "Apache 2.0",
				url = "license url"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "Spring boot user management doc",
				url = "http://java-guides.com"
				)
)
public class SpringbootRestfulWebservicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootRestfulWebservicesApplication.class, args);
	}

}
