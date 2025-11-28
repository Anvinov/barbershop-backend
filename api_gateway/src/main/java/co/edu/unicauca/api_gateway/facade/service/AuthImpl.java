package co.edu.unicauca.api_gateway.facade.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import co.edu.unicauca.api_gateway.data.entity.ERole;
import co.edu.unicauca.api_gateway.data.entity.Role;
import co.edu.unicauca.api_gateway.data.entity.User;
import co.edu.unicauca.api_gateway.data.repository.RoleRepository;
import co.edu.unicauca.api_gateway.data.repository.UserRepository;
import co.edu.unicauca.api_gateway.facade.DTO.client.ClientRequestDTO;
import co.edu.unicauca.api_gateway.facade.DTO.gateway.LoginRequestDTO;
import co.edu.unicauca.api_gateway.facade.DTO.gateway.SignupRequestDTO;
import co.edu.unicauca.api_gateway.facade.DTO.gateway.JwtResponseDTO;
import co.edu.unicauca.api_gateway.facade.DTO.gateway.MessageResponseDTO;
import co.edu.unicauca.api_gateway.facade.client.ClientClient;
import co.edu.unicauca.api_gateway.security.jwt.JwtUtils;
import co.edu.unicauca.api_gateway.security.service.UserDetailsImpl;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.authentication.AuthenticationManager;


@Service
public class AuthImpl implements AuthInt{

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder encoder;

    private final ClientClient clientClient;

    public AuthImpl(AuthenticationManager authenticationManager, JwtUtils jwtUtils, UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder encoder, ClientClient clientClient) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
        this.clientClient = clientClient;
    }


    @Override
    public JwtResponseDTO authenticateUser(LoginRequestDTO loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
            .map(item -> item.getAuthority())
            .collect(Collectors.toList());

        return new JwtResponseDTO(
                jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                roles
        );
    }

    @Override
    public MessageResponseDTO registerUser(SignupRequestDTO signUpRequest) {
       
        User user = new User(signUpRequest.getEmail(),encoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_CLIENT)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                    break;
                    case "barber":
                        Role modRole = roleRepository.findByName(ERole.ROLE_BARBER)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);

                    break;
                    default:
                        clientClient.create(new ClientRequestDTO(
                                signUpRequest.getName(),
                                signUpRequest.getPhone(),
                                signUpRequest.getEmail()
                        ));

                        Role userRole = roleRepository.findByName(ERole.ROLE_CLIENT)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);

        userRepository.save(user);

        return new MessageResponseDTO("User "+user.getEmail()+" created successfully");
    }

}
