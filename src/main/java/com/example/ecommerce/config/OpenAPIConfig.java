package com.example.ecommerce.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI ecommerceOpenAPI() {

        Info info = new Info()
                .title("E-Commerce API")
                .description(
                    "Complete E-Commerce REST API with authentication, "
                    + "products, cart, orders, and reviews"
                )
                .version("1.0.0")
                .contact(new Contact()
                        .name("E-Commerce Team")
                        .email("support@ecommerce.com")
                        .url("https://ecommerce.com"));

        SecurityScheme securityScheme = new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT")
                .description("JWT token");

        SecurityRequirement securityRequirement =
                new SecurityRequirement()
                        .addList("bearerAuth");

        return new OpenAPI()
                .info(info)
                .addSecurityItem(securityRequirement)
                .components(
                        new Components()
                                .addSecuritySchemes(
                                        "bearerAuth",
                                        securityScheme
                                )
                );
    }
}