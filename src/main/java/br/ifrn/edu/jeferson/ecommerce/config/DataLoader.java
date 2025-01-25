package br.ifrn.edu.jeferson.ecommerce.config;

import br.ifrn.edu.jeferson.ecommerce.domain.Role;
import br.ifrn.edu.jeferson.ecommerce.domain.User;
import br.ifrn.edu.jeferson.ecommerce.repository.RoleRepository;
import br.ifrn.edu.jeferson.ecommerce.repository.UserRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class DataLoader {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder encoder;

    public DataLoader(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
    }

    @EventListener
    public void onApplicationReady(ApplicationReadyEvent event) {
        if (roleRepository.count() == 0) {
            roleRepository.saveAll(Set.of(
                    new Role(null, "ROLE_ADMIN"),
                    new Role(null, "ROLE_MANAGER"),
                    new Role(null, "ROLE_USER")
            ));
        }

        if (userRepository.count() == 0) {
            Role adminRole = roleRepository.findByName("ROLE_ADMIN");
            Role userRole = roleRepository.findByName("ROLE_USER");

            User adminUser = new User();
            adminUser.setUsername("admin");
            adminUser.setPassword(encoder.encode("123456"));
            adminUser.setRoles(Set.of(adminRole, userRole));
            userRepository.save(adminUser);
        }
    }
}
