package br.ifrn.edu.jeferson.ecommerce.config;

import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KeycloakConfig {

    @Value("${spring.security.oauth2.resourceserver.jwt.issuer-uri}")
    private String issuerUri;

    @Bean
    public Keycloak keycloakAdmin() {
        // Remove /realms/ecommerce da URL do issuer
        String serverUrl = issuerUri.substring(0, issuerUri.indexOf("/realms"));

        return KeycloakBuilder.builder()
                .serverUrl(serverUrl)
                .realm("ecommerce")
                .clientId("backend-service") // seu client específico para o backend
                .clientSecret("NxYiiZbPjKja3vpSX9XPWyhb6ndpajnV")
                .grantType(OAuth2Constants.CLIENT_CREDENTIALS)
                .build();
    }
}