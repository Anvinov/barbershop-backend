package co.edu.unicauca.api_gateway.facade.client;

import co.edu.unicauca.api_gateway.facade.DTO.client.request.ClientRequestDTO;
import co.edu.unicauca.api_gateway.facade.DTO.client.response.ClientResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(
    name = "client-service",
    url = "http://localhost:8082/api/client"
)
public interface  ClientClient {

    @GetMapping("/health")
    ResponseEntity<?> getHealth();

    @PostMapping
    ResponseEntity<?> createClient(@RequestBody ClientRequestDTO request);

    @GetMapping("/{id}")
    ResponseEntity<?> getClientById(@PathVariable Long id);

    @GetMapping("/email")
    ResponseEntity<ClientResponseDTO> getClientByEmail(@RequestParam String email);

    @PutMapping("/{id}")
    ResponseEntity<?> updateClient(@PathVariable Long id, @RequestBody ClientRequestDTO request);

    @PutMapping("/disable/{id}")
    ResponseEntity<?> disableClient(@PathVariable Long id);

}
