package com.example.ecommercebe.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {
    @Bean
    public OpenAPI myOpenAPI() {
        Contact contact = new Contact();
        contact.setEmail("student@gmail.com");
        contact.setName("Student name");
        contact.setUrl("https://www.google.com");

        License mitLicense = new License().name("MIT License").url("https://choosealicense.com/licenses/mit/");

        Info info = new Info()
                .title("Student Management API")
                .version("1.0")
                .description("This is a simple student management API")
                .termsOfService("https://www.google.com")
                .contact(contact)
                .license(mitLicense);

        return new OpenAPI().info(info);
    }
}