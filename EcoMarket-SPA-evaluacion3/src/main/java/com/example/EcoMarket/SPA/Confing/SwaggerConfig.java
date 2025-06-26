package com.example.EcoMarket.SPA.Confing;

import java.util.List;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI ecoMarketOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("EcoMarket SPA - API")
                        .description("Documentación de la API REST para el sistema de gestión de pedidos, productos y usuarios de EcoMarket SPA.")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Equipo de Desarrollo EcoMarket")
                                .email("soporte@ecomarket.cl")
                                .url("https://ecomarket.cl")
                        )
                )
                .servers(List.of(
                        new Server().url("http://localhost:8080").description("Servidor local")
                ));
    }

    // Opcional: agrupar APIs
    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("ecomarket-public")
                .pathsToMatch("/api/**")
                .build();
    }
}
