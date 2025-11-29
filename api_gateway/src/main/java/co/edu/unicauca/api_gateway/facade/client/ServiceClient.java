package co.edu.unicauca.api_gateway.facade.client;

import co.edu.unicauca.api_gateway.facade.DTO.service.request.CategoryRequestDTO;
import co.edu.unicauca.api_gateway.facade.DTO.service.request.ServiceRequestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(
        name = "service-service",
        url = "http://localhost:8084/api/service"
)
public interface ServiceClient {
    @GetMapping("/health")
    ResponseEntity<?> getHealth();

    // Category endpoints
    @PostMapping("/category")
    ResponseEntity<?> createCategory(@RequestBody CategoryRequestDTO request);

    @GetMapping("/category")
    ResponseEntity<?> getAllCategories();

    @GetMapping("/category/search")
    ResponseEntity<?> getCategoryByName(@RequestParam String name);

    // Service endpoints
    @PostMapping
    ResponseEntity<?> createService(@RequestBody ServiceRequestDTO request);

    @PutMapping("/{id}")
    ResponseEntity<?> updateService(@PathVariable Long id, @RequestBody ServiceRequestDTO request);

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteService(@PathVariable Long id);

    @GetMapping
    ResponseEntity<?> getServices(@RequestParam(required = false) String category);

    @GetMapping("/{id}")
    ResponseEntity<?> getServiceById(@PathVariable Long id);

    @PutMapping("/disable/{id}")
    ResponseEntity<?> disableService(@PathVariable Long id);

    @PutMapping("/enable/{id}")
    ResponseEntity<?> enableService(@PathVariable Long id);
}
