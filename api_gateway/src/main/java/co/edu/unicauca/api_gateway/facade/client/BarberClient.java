package co.edu.unicauca.api_gateway.facade.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(
        name = "barber-service",
        url = "http://localhost:8083/api/barber"
)
public interface BarberClient {

    @GetMapping("/health")
    ResponseEntity<?> getHealth();






}
