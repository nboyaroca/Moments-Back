package com.factoria.moments.auth.configuration;

import com.factoria.moments.models.Role;
import com.factoria.moments.models.User;
import com.factoria.moments.repositories.AuthRepository;
import com.factoria.moments.repositories.RoleRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

;

@Component
public class RoleRepositoryInitializer {

    private RoleRepository roleRepository;
    private AuthRepository authRepository;
    private PasswordEncoder encoder;

    public RoleRepositoryInitializer(RoleRepository roleRepository, AuthRepository authRepository, PasswordEncoder encoder) {
        this.roleRepository = roleRepository;
        this.authRepository = authRepository;
        this.encoder = encoder;
    }

    @PostConstruct
    public void addAvailableRoles(){
        if (!roleRepository.findAll().isEmpty()) {
            return;
        }

        List<Role> roles = List.of(
                new Role(1, Role.RoleName.ROLE_ADMIN),

                new Role(2, Role.RoleName.ROLE_USER)
        );

        roleRepository.saveAll(roles);

        if (!authRepository.findAll().isEmpty()) {
            return;
        }

        var user = new User();
        user.setRoles(roleRepository.findAll().stream().collect(Collectors.toSet()));
        user.setEmail("admin@admin.com");
        user.setUsername("Admin");
        user.setPassword(encoder.encode("12345678"));
        user.setAvatar("https://cdn4.iconfinder.com/data/icons/profession-avatar-5/64/13-astronaut-512.png");

        authRepository.save(user);
    }
}
