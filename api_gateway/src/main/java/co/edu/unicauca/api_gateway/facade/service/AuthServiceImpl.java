package co.edu.unicauca.api_gateway.facade.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import co.edu.unicauca.api_gateway.data.entity.ERole;
import co.edu.unicauca.api_gateway.data.entity.Role;
import co.edu.unicauca.api_gateway.data.entity.User;
import co.edu.unicauca.api_gateway.data.repository.RoleRepository;
import co.edu.unicauca.api_gateway.data.repository.UserRepository;
import co.edu.unicauca.api_gateway.facade.DTO.auth.request.SignUpBarberRequestDTO;
import co.edu.unicauca.api_gateway.facade.DTO.auth.request.SignupClientRequestDTO;
import co.edu.unicauca.api_gateway.facade.DTO.auth.response.JwtResponseDTO;
import co.edu.unicauca.api_gateway.facade.DTO.auth.request.LoginRequestDTO;
import co.edu.unicauca.api_gateway.facade.DTO.auth.response.MessageResponseDTO;
import co.edu.unicauca.api_gateway.facade.DTO.barber.request.BarberRequestDTO;
import co.edu.unicauca.api_gateway.facade.DTO.barber.response.BarberResponseDTO;
import co.edu.unicauca.api_gateway.facade.DTO.barber.request.BarberSimpleRequestDTO;
import co.edu.unicauca.api_gateway.facade.DTO.client.request.ClientRequestDTO;
import co.edu.unicauca.api_gateway.facade.DTO.client.response.ClientResponseDTO;
import co.edu.unicauca.api_gateway.facade.client.BarberClient;
import co.edu.unicauca.api_gateway.facade.client.ClientClient;
import co.edu.unicauca.api_gateway.security.exception.EmailAlreadyExistsException;
import co.edu.unicauca.api_gateway.security.exception.UserNotFoundException;
import co.edu.unicauca.api_gateway.security.jwt.JwtUtils;
import co.edu.unicauca.api_gateway.security.service.UserDetailsImpl;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.authentication.AuthenticationManager;


@Service
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder encoder;

    private final ClientClient clientClient;
    private final BarberClient  barberClient;

    public AuthServiceImpl(AuthenticationManager authenticationManager, JwtUtils jwtUtils, UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder encoder, ClientClient clientClient, BarberClient barberClient) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.encoder = encoder;

        this.clientClient = clientClient;
        this.barberClient = barberClient;
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

        AtomicReference<String> name = new AtomicReference<>("");

        roles.forEach(role -> {
            switch (role) {
                case "ROLE_ADMIN":

                    break;
                case "ROLE_BARBER":
                    BarberResponseDTO barber = barberClient.getBarberByEmail(userDetails.getUsername()).getBody();
                    name.set(barber.getName());
                    break;
                case "ROLE_CLIENT":
                    ClientResponseDTO client = clientClient.getClientByEmail(userDetails.getUsername()).getBody();
                    name.set(client.getName());
                    break;
                default:
                    throw new RuntimeException("Error: Role is not found.");
            }

        });


        return new JwtResponseDTO(
                jwt,
                userDetails.getId(),
                name.toString(),
                userDetails.getUsername(),
                roles
        );
    }

    @Override
    public MessageResponseDTO registerClient(SignupClientRequestDTO signUpRequest) {
        User user = new User(signUpRequest.getEmail(),encoder.encode(signUpRequest.getPassword()));

        Set<Role> roles = new HashSet<>();

        clientClient.createClient(new ClientRequestDTO(
                signUpRequest.getName(),
                signUpRequest.getPhone(),
                signUpRequest.getEmail()
        ));

        Role userRole = roleRepository.findByName(ERole.ROLE_CLIENT)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));

        roles.add(userRole);

        user.setRoles(roles);

        userRepository.save(user);

        return new MessageResponseDTO("Client "+user.getEmail()+" created successfully");
    }

    @Override
    public MessageResponseDTO registerBarber(SignUpBarberRequestDTO signUpRequest) {
        User user = new User(signUpRequest.getEmail(),encoder.encode(signUpRequest.getPassword()));

        Set<Role> roles = new HashSet<>();

        barberClient.createBarber(new BarberRequestDTO(
                signUpRequest.getName(),
                signUpRequest.getPhone(),
                signUpRequest.getEmail(),
                signUpRequest.getSchedule()
        ));

        Role userRole = roleRepository.findByName(ERole.ROLE_BARBER)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));

        roles.add(userRole);

        user.setRoles(roles);

        userRepository.save(user);

        return new MessageResponseDTO("Barber "+user.getEmail()+" created successfully");
    }

    @Override
    public MessageResponseDTO updateUser(Long id, ClientRequestDTO clientRequest) {

        if(userRepository.existsByEmail(clientRequest.getEmail())) {
            throw new EmailAlreadyExistsException(clientRequest.getEmail());
        }

        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        Set<Role> roles = user.getRoles();

        roles.forEach(role -> {
            switch (role.getName()) {
                case ROLE_BARBER:
                    BarberResponseDTO barber = (BarberResponseDTO) barberClient.getBarberByEmail(user.getEmail()).getBody();
                    barberClient.updateBarber(barber.getId(), new BarberSimpleRequestDTO(
                            barber.getName(),
                            barber.getPhone(),
                            barber.getEmail()
                    ));
                    break;
                case ROLE_CLIENT:
                    ClientResponseDTO client = clientClient.getClientByEmail(user.getEmail()).getBody();
                    clientClient.updateClient(client.getId(), clientRequest);
                    break;
                default:
                    throw new RuntimeException("Error: Role is not found.");
            }

        });

        user.setEmail(clientRequest.getEmail());

        userRepository.save(user);

        return new MessageResponseDTO("User "+user.getEmail()+" updated successfully");
    }

    @Override
    public MessageResponseDTO updatePassword(Long id, String password) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        user.setPassword(encoder.encode(password));

        userRepository.save(user);

        return new MessageResponseDTO("Password updated successfully");
    }

}
