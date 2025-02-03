package br.ifrn.edu.jeferson.ecommerce.security.service;

import lombok.extern.slf4j.Slf4j;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;

import java.util.*;
@Service
@Slf4j
public class KeycloakUserService {

    private final Keycloak keycloak;

    public KeycloakUserService(Keycloak keycloak) {
        this.keycloak = keycloak;
    }

    public void updateUserAttributes(String userId, Map<String, List<String>> attributes) {
        try {
            // Pega a representação do usuário
            UserResource userResource = keycloak.realm("ecommerce").users().get(userId);
            UserRepresentation user = userResource.toRepresentation();

            // Log para debug
            log.debug("Atualizando atributos para usuário: {}", userId);

            // Atualiza os atributos
            user.setAttributes(attributes);

            // Salva as alterações
            userResource.update(user);

        } catch (Exception e) {
            log.error("Erro ao atualizar atributos do usuário: {}", e.getMessage(), e);
            throw new RuntimeException("Erro ao atualizar informações do usuário", e);
        }
    }

    public UserRepresentation getUserInfo(String userId) {
        try {
            UserResource userResource = keycloak.realm("ecommerce").users().get(userId);
            return userResource.toRepresentation();
        } catch (Exception e) {
            log.error("Erro ao buscar usuário: {}", e.getMessage(), e);
            throw new RuntimeException("Erro ao buscar informações do usuário", e);
        }
    }
}