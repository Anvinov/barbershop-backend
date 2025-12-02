package co.edu.unicauca.api_gateway.data.start;

import co.edu.unicauca.api_gateway.data.entity.ERole;
import co.edu.unicauca.api_gateway.data.entity.Role;
import co.edu.unicauca.api_gateway.data.entity.User;
import co.edu.unicauca.api_gateway.data.repository.RoleRepository;
import co.edu.unicauca.api_gateway.data.repository.UserRepository;
import co.edu.unicauca.api_gateway.facade.DTO.client.request.ClientRequestDTO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class StartupRunner implements CommandLineRunner {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    private final PasswordEncoder encoder;

    public StartupRunner(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
    }

    @Override
    public void run(String... args) throws Exception {
        if (!userRepository.existsByEmail("admin@admin.com")) ejecutarTareaInicial();
    }

    private void ejecutarTareaInicial() {
        System.out.println("Agregando el usuario administrador");

        User user = new User("admin@admin.com",encoder.encode("admin123"));

        Set<Role> roles = new HashSet<>();

        Role userRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));

        roles.add(userRole);

        user.setRoles(roles);

        userRepository.save(user);
    }
}

