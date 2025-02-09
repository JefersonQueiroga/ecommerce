package br.ifrn.edu.jeferson.ecommerce.controller;

import br.ifrn.edu.jeferson.ecommerce.security.service.KeycloakUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import lombok.Data;
import java.util.HashMap;

@RestController
@RequestMapping("/api/users")
@Slf4j
public class UserProfileController {

    private final KeycloakUserService keycloakUserService;

    public UserProfileController(KeycloakUserService keycloakUserService) {
        this.keycloakUserService = keycloakUserService;
    }

    // DTO para os dados do perfil
    @Data
    public static class UserProfileDTO {
        private String endereco;
        private String cidade;
        private String telefone;
    }

    // adicionando novos informações ao perfil do usuário
    @PostMapping("/profile")
    public ResponseEntity<Void> updateProfile(
            @AuthenticationPrincipal Jwt jwt,
            @RequestBody UserProfileDTO profileData) {

        try {
            String userId = jwt.getSubject();

            // Converte DTO para o formato de atributos do Keycloak
            Map<String, List<String>> attributes = new HashMap<>();
            attributes.put("cidade", Collections.singletonList(profileData.getCidade()));
            attributes.put("endereco", Collections.singletonList(profileData.getEndereco()));
            attributes.put("telefone", Collections.singletonList(profileData.getTelefone()));

            keycloakUserService.updateUserAttributes(userId, attributes);

            return ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("Erro ao atualizar perfil: {}", e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }
    }
}