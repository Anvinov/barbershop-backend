package co.edu.unicauca.reservation_service.infra.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(
        name = "service-service",
        url = "http://localhost:8084/api/service"
)
public interface ServiceClient {

    @PostMapping("/disabled")
    ResponseEntity<List<Long>> servicesAreDisabled(@RequestBody List<Long> ids);

}
