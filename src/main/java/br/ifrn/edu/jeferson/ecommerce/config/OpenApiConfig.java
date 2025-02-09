package br.ifrn.edu.jeferson.ecommerce.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        final String securitySchemeName = "bearerAuth"; // Nome do esquema de segurança

        return new OpenAPI()
                .info(new Info()
                        .title("API de E-commerce")
                        .version("1.0.0")
                        .description("API para gerenciamento de categorias e produtos da Empresa Queiroga"))
                .components(new Components()
                        .addSecuritySchemes(securitySchemeName,
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT"))) // Esquema de autenticação JWT
                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName)); // Aplicar segurança globalmente
    }
}
