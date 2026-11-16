package com.nek.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfig {

    @Bean
    OpenAPI apiInfo() {
        return new OpenAPI().info(new Info()
                .title("Users API")
                .version("1.0.0")
                .description("API REST de usuarios con Spring Boot 3: paginación, filtros, validación y errores RFC 9457.")
                .contact(new Contact().name("Andrés Cárdenas").url("https://nekdress.com"))
                .license(new License().name("MIT")));
    }
}
