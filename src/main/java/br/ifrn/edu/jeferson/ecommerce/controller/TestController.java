package br.ifrn.edu.jeferson.ecommerce.controller;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/test")
public class TestController {

    @GetMapping("/roles")
    public ResponseEntity<?> testRoles(@AuthenticationPrincipal Jwt jwt) {
        Map<String, Object> response = new HashMap<>();

        // Acessando as roles do realm_access
        Map<String, Object> realmAccess = jwt.getClaimAsMap("realm_access");
        if (realmAccess != null) {
            @SuppressWarnings("unchecked")
            List<String> roles = (List<String>) realmAccess.get("roles");
            response.put("roles", roles);
        }

        response.put("allClaims", jwt.getClaims());

        return ResponseEntity.ok(response);
    }
}