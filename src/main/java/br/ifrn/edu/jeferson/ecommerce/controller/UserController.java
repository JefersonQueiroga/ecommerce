package br.ifrn.edu.jeferson.ecommerce.controller;

import br.ifrn.edu.jeferson.ecommerce.security.service.KeycloakUserService;
import br.ifrn.edu.jeferson.ecommerce.security.service.SecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.keycloak.representations.idm.UserRepresentation;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final SecurityService securityService;
    private final KeycloakUserService keycloakUserService;

    @GetMapping("/info")
    public ResponseEntity<Map<String, Object>> getUserInfo(@AuthenticationPrincipal Jwt jwt) {
        try {
            var userInfo = new HashMap<String, Object>();

            // Dados básicos do token
            userInfo.put("username", jwt.getClaimAsString("preferred_username"));
            userInfo.put("email", jwt.getClaimAsString("email"));
            userInfo.put("firstName", jwt.getClaimAsString("given_name"));
            userInfo.put("lastName", jwt.getClaimAsString("family_name"));
            userInfo.put("roles", securityService.getCurrentUserRoles());

            // Buscar dados personalizados do Keycloak
            String userId = jwt.getSubject();
            UserRepresentation user = keycloakUserService.getUserInfo(userId);

            // Adicionar atributos personalizados específicos
            Map<String, List<String>> attributes = user.getAttributes();
            if (attributes != null) {
                userInfo.put("nomeDaMae", getFirstAttributeValue(attributes, "nomeDaMae"));
                userInfo.put("cidade", getFirstAttributeValue(attributes, "cidade"));
                userInfo.put("estado", getFirstAttributeValue(attributes, "estado"));
                userInfo.put("celular", getFirstAttributeValue(attributes, "celular"));
            }

            return ResponseEntity.ok(userInfo);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    private String getFirstAttributeValue(Map<String, List<String>> attributes, String key) {
        List<String> values = attributes.get(key);
        return values != null && !values.isEmpty() ? values.get(0) : null;
    }

    @GetMapping("/roles")
    public ResponseEntity<Collection<String>> getUserRoles() {
        return ResponseEntity.ok(securityService.getCurrentUserRoles());
    }
}