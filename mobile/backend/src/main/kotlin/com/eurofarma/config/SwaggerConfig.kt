package com.eurofarma.config

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Contact
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.security.SecurityRequirement
import io.swagger.v3.oas.models.security.SecurityScheme
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfig {

    @Bean
    fun openAPI(): OpenAPI = OpenAPI()
        .info(
            Info()
                .title("Eurofarma Training API")
                .description("API REST para plataforma de treinamento corporativo")
                .version("1.0.0")
                .contact(Contact().name("Equipe Dev").email("dev@eurofarma.com.br"))
        )
        .addSecurityItem(SecurityRequirement().addList("bearerAuth"))
        .components(
            io.swagger.v3.oas.models.Components()
                .addSecuritySchemes("bearerAuth",
                    SecurityScheme()
                        .type(SecurityScheme.Type.HTTP)
                        .scheme("bearer")
                        .bearerFormat("JWT")
                )
        )
}
