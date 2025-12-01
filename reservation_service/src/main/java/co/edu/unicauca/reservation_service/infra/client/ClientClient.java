package co.edu.unicauca.reservation_service.infra.client;

import co.edu.unicauca.reservation_service.infra.dto.client.response.ClientResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(
    name = "client-service",
    url = "http://localhost:8082/api/client"
)
public interface  ClientClient {

    @GetMapping("/{id}")
    ResponseEntity<ClientResponseDTO> getClientById(@PathVariable Long id);

}
