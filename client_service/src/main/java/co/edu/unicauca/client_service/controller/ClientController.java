package co.edu.unicauca.client_service.controller;

import co.edu.unicauca.client_service.infra.dto.ClientRequestDTO;
import co.edu.unicauca.client_service.infra.dto.ClientResponseDTO;
import co.edu.unicauca.client_service.service.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/client")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/health")
    public ResponseEntity<?> getHealth() {
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<?> createClient(@RequestBody ClientRequestDTO request) {
        ClientResponseDTO client = clientService.createClient(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(client);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getClientById(@PathVariable Long id) {
        ClientResponseDTO client = clientService.getClientById(id);
        return ResponseEntity.ok(client);
    }

    @GetMapping("/email")
    public ResponseEntity<?> getClientByEmail(@RequestParam String email) {
        ClientResponseDTO client = clientService.getClientByEmail(email);
        return ResponseEntity.ok(client);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateClient(
            @PathVariable Long id,
            @RequestBody ClientRequestDTO request) {
        ClientResponseDTO client = clientService.updateClient(id, request);
        return ResponseEntity.ok(client);
    }

    @PutMapping("/disable/{id}")
    public ResponseEntity<?> disableClient(@PathVariable Long id) {
        ClientResponseDTO client = clientService.disableClient(id);
        return ResponseEntity.ok(client);
    }
}

