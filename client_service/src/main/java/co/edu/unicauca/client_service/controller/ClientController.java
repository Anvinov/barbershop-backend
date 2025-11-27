package co.edu.unicauca.client_service.controller;

import co.edu.unicauca.client_service.infra.dto.ClientRequestDTO;
import co.edu.unicauca.client_service.infra.dto.ClientResponseDTO;
import co.edu.unicauca.client_service.service.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    private final ClientService service;

    public ClientController(ClientService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody ClientRequestDTO request) {
        ClientResponseDTO client = service.createClient(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(client);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        ClientResponseDTO client = service.getClientById(id);
        return ResponseEntity.ok(client);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(
            @PathVariable Long id,
            @RequestBody ClientRequestDTO request) {
        ClientResponseDTO client = service.updateClient(id, request);
        return ResponseEntity.ok(client);
    }

    @PutMapping("/disable/{id}")
    public ResponseEntity<?> disableClient(@PathVariable Long id) {
        ClientResponseDTO client = service.disableClient(id);
        return ResponseEntity.ok(client);
    }
}

