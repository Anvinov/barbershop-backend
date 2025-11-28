package co.edu.unicauca.api_gateway.controller;

import co.edu.unicauca.api_gateway.facade.DTO.client.ClientRequestDTO;
import co.edu.unicauca.api_gateway.facade.client.ClientClient;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials = "true")
@RestController
@RequestMapping("/api/client")
public class ClientController {

    private final ClientClient clientClient;

    public ClientController(ClientClient clientClient) {
        this.clientClient = clientClient;
    }

    @GetMapping("/health")
    public ResponseEntity<?> getHealth() {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return clientClient.getById(id);
    }

    @GetMapping("/email")
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<?> getByEmail(@RequestParam String email){
        return clientClient.getByEmail(email);
    }

    @PutMapping("/disable/{id}")
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<?> disableClient(@PathVariable Long id) {
        return clientClient.disableClient(id);
    }
}
