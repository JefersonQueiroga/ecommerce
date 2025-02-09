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

    @Value("${keycloak.client-secret}")
    private String clientSecret;

    @Value("${keycloak.client-id}")
    private String clientId;

    @Bean
    public Keycloak keycloak() {
        // Remove /realms/ecommerce da URL do issuer
        String serverUrl = issuerUri.substring(0, issuerUri.indexOf("/realms"));

        return KeycloakBuilder.builder()
                .serverUrl(serverUrl)
                .realm("ecommerce")
                .clientId(clientId)
                .clientSecret(clientSecret)
                .grantType(OAuth2Constants.CLIENT_CREDENTIALS)
                .scope("openid")
                .build();
    }
}