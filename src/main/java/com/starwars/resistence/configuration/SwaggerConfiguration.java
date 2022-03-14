package com.starwars.resistence.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(
                        new Info()
                                .title("Star Wars Resistance")
                                .description("O império continua sua luta incessante de dominar a galáxia, tentando ao máximo expandir seu território\n" +
                                        "        e eliminar os rebeldes. Você, como um soldado da resistência, foi designado para desenvolver um sistema para\n" +
                                        "        compartilhar recursos entre os rebeldes.")
                                .version("v0.0.1")
                                .license(
                                        new License().name("Apache 2.0").url("http://springdoc.org")
                                ).contact(
                                        new Contact()
                                                .name("Alan Patrik")
                                                .email("alanpatrik-fragozo@gmail.com")
                                                .url("https://github.com/Alan-Patrik")
                                )
                );
    }
}
